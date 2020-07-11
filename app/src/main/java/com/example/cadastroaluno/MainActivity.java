package com.example.cadastroaluno;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    sObjetosTela soObjetosTela = new sObjetosTela();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Faz a ligação entre o java e o arquivo xml
        this.soObjetosTela.edtNome = findViewById(R.id.edtNome);
        this.soObjetosTela.edtRA = findViewById(R.id.edtRA);
        this.soObjetosTela.rdbFeminino = findViewById(R.id.rdbFeminino);
        this.soObjetosTela.rdbMasculino = findViewById(R.id.rdbMasculino);
        this.soObjetosTela.spiEstado = findViewById(R.id.spiEstado);
        this.soObjetosTela.chkPraticaEsporte = findViewById(R.id.chkPraticaEsporte);
        this.soObjetosTela.chkFuma = findViewById(R.id.chkFuma);
        this.soObjetosTela.edtNumeroFilhos = findViewById(R.id.edtNumeroFilhos);
        this.soObjetosTela.btnLimpar = findViewById(R.id.btnLimpar);
        this.soObjetosTela.btnSalvar = findViewById(R.id.btnSalvar);

        //Coloca os caracteres em letra maiúscula no nome
        this.soObjetosTela.edtNome.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
    }

    public void buscar(View view) {
        SharedPreferences vetorChave = getSharedPreferences("aluno", Context.MODE_PRIVATE);

        String ra = vetorChave.getString("ra","");
        this.soObjetosTela.edtRA.setText(ra);

        String nome = vetorChave.getString("nome","");
        this.soObjetosTela.edtNome.setText(nome);

        String sexo = ((SharedPreferences) vetorChave).getString("sexo","");
        if (sexo == "FEMININO") {
            this.soObjetosTela.rdbFeminino.setChecked(true);
            this.soObjetosTela.rdbMasculino.setChecked(false);
        }else {
            this.soObjetosTela.rdbFeminino.setChecked(false);
            this.soObjetosTela.rdbMasculino.setChecked(true);
        }
        String esporte = vetorChave.getString("esporte","");
        if (esporte =="SIM")
            this.soObjetosTela.chkPraticaEsporte.setChecked(true);
        else
            this.soObjetosTela.chkPraticaEsporte.setChecked(false);

        String fuma = vetorChave.getString("fuma","");
        if (fuma =="SIM")
            this.soObjetosTela.chkFuma.setChecked(true);
        else
            this.soObjetosTela.chkFuma.setChecked(false);

        String numFilhos = vetorChave.getString("numFilhos","");
        this.soObjetosTela.edtNumeroFilhos.setText(numFilhos);
    }

    public void salvar(View view) {
        SharedPreferences vetorChave = getSharedPreferences("aluno", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = vetorChave.edit();

        editor.putString("ra",this.soObjetosTela.edtRA.getText().toString());
        editor.putString("nome",this.soObjetosTela.edtNome.getText().toString());
        if (this.soObjetosTela.rdbFeminino.isChecked())
            editor.putString("sexo","FEMININO");
        else
            editor.putString("sexo","MASCULINO");

        if (this.soObjetosTela.chkPraticaEsporte.isChecked())
            editor.putString("esporte","SIM");
        else
            editor.putString("esporte","NAO");

        if (this.soObjetosTela.chkFuma.isChecked())
            editor.putString("fuma","SIM");
        else
            editor.putString("fuma","NAO");

        editor.putString("numFilhos",this.soObjetosTela.edtNumeroFilhos.getText().toString());
        editor.apply();
    }

    public void limparCampos(View view) {

        this.soObjetosTela.edtNome.setText("");
        this.soObjetosTela.edtRA.setText("");
        this.soObjetosTela.rdbFeminino.setChecked(false);
        this.soObjetosTela.rdbMasculino.setChecked(false);
        this.soObjetosTela.chkPraticaEsporte.setChecked(false);
        this.soObjetosTela.chkFuma.setChecked(false);
        this.soObjetosTela.edtNumeroFilhos.setText("");
    }

    private static class sObjetosTela{
        //Objetos utilizados na tela xml
        EditText edtRA;
        EditText edtNome;
        RadioButton rdbFeminino;
        RadioButton rdbMasculino;
        Spinner spiEstado;
        CheckBox chkPraticaEsporte;
        CheckBox chkFuma;
        EditText edtNumeroFilhos;
        Button btnLimpar;
        Button btnSalvar;
        Button btnBuscar;
    }
}
