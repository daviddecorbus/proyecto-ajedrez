package com.telefonica.first.tableroprueba;


import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


public class AdaptadorEjercicios extends ArrayAdapter {

    Ejercicio [] ejercicios;

    public AdaptadorEjercicios(Context context, int textViewResourceId, Ejercicio [] ejercicios) {
        super(context, textViewResourceId, ejercicios);
      this.ejercicios = ejercicios;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = inflater.inflate(R.layout.elementos_ejercicio, null);
        TextView textView = (TextView) v.findViewById(R.id.nivel);
        textView.setText(ejercicios[position].getNivel() + " " +ejercicios[position].getEstado());
        switch (ejercicios[position].getEstado()){
            case "sin empezar":
                textView.setBackgroundColor(Color.RED);
                break;
            case "empezada":
                textView.setBackgroundColor(Color.YELLOW);
            break;
            case "terminada":
                textView.setBackgroundColor(Color.GREEN);
                break;

            default:
                break;
        }
        return v;

    }

}