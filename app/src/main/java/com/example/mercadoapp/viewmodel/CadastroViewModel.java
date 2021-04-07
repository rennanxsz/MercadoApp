package com.example.mercadoapp.viewmodel;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mercadoapp.activity.CadastroActivity;
import com.example.mercadoapp.config.ConfiguracaoFirebase;
import com.example.mercadoapp.model.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

public class CadastroViewModel extends ViewModel {

    private FirebaseAuth autenticacao;
    private String excecao;

    public String getExcecao() {
        return excecao;
    }

    public MutableLiveData<Boolean> getUsuarioCadastrado() {
        return usuarioCadastrado;
    }

    MutableLiveData<Boolean> usuarioCadastrado = new MutableLiveData<>();

    public void cadastrarUsuario(final Context context, Usuario usuario) {

        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        autenticacao.createUserWithEmailAndPassword(
                usuario.getEmail(), usuario.getSenha()
        ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {

                     usuarioCadastrado.postValue(true);

                } else {

                    try {

                        throw task.getException();

                    } catch (FirebaseAuthWeakPasswordException e) {
                        excecao = "Digite uma senha mais Forte! ";
                    } catch (FirebaseAuthInvalidCredentialsException e) {
                        excecao = "Por favor, digite um e-mail valido!";
                    } catch (FirebaseAuthUserCollisionException e) {
                        excecao = "Está conta já foi cadastrada";
                    } catch (Exception e) {
                        excecao = "Erro ao cadastrar usuário" + e.getMessage();
                        e.printStackTrace();
                    }

                    Toast.makeText(context,
                            excecao,
                            Toast.LENGTH_SHORT).show();

                    usuarioCadastrado.postValue(false);

                }
            }
        });
    }

}
