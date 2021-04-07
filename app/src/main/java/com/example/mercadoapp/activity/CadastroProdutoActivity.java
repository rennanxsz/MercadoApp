package com.example.mercadoapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.mercadoapp.R;
import com.example.mercadoapp.model.Produto;
import com.example.mercadoapp.viewmodel.CadastroProdutoViewModel;

public class CadastroProdutoActivity extends AppCompatActivity {

    private EditText textoProduto, textoQuantidade;
    private Button botaoCadastrar;
    private CadastroProdutoViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_produto);

        textoProduto = findViewById(R.id.editProduto);
        textoQuantidade = findViewById(R.id.editQuantidade);
        botaoCadastrar = findViewById(R.id.buttonCadastrar);
        viewModel = ViewModelProviders.of(this ).get(CadastroProdutoViewModel.class);


        botaoCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            String produto = textoProduto.getText().toString();
            String quantidade = textoQuantidade.getText().toString();

                Produto produto1 = new Produto(produto, quantidade);
                viewModel.cadastrarProduto(produto1);

            }
        });


    }


}