package com.telefonica.first.tableroprueba;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class AdaptadorMenu extends BaseExpandableListAdapter {

    private Context contexto;
    private ArrayList<InformacionGrupo> grupos;

    public AdaptadorMenu(Context contexto, ArrayList<InformacionGrupo> grupos) {
        this.contexto = contexto;
        this.grupos = grupos;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        ArrayList<ChildInfo> submenus = grupos.get(groupPosition).getSubmenus();
        return submenus.get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild,
                             View view, ViewGroup parent) {

        ChildInfo informacionSubmenu = (ChildInfo) getChild(groupPosition, childPosition);
        if (view == null) {
            LayoutInflater infalInflater = (LayoutInflater) contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = infalInflater.inflate(R.layout.submenus, null);
        }

        //Añade una imagen
        ImageView imagen = (ImageView) view.findViewById(R.id.image);
        imagen.setImageResource(informacionSubmenu.getImagen());

        //Añade un texto
        TextView nombre = (TextView) view.findViewById(R.id.childItem);
        nombre.setText(informacionSubmenu.getNombre().trim());

        return view;
    }

    @Override
    public int getChildrenCount(int groupPosition) {

        ArrayList<ChildInfo> submenus = grupos.get(groupPosition).getSubmenus();
        return submenus.size();

    }

    @Override
    public Object getGroup(int groupPosition) {
        return grupos.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return grupos.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isLastChild, View view,
                             ViewGroup parent) {

        InformacionGrupo cabecera = (InformacionGrupo) getGroup(groupPosition);
        if (view == null) {
            LayoutInflater inf = (LayoutInflater) contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inf.inflate(R.layout.grupo_menu, null);
        }

        //Añade la cabecera de los Ménus
        TextView cabeceraMenu = (TextView) view.findViewById(R.id.cabeceraMenu);
        cabeceraMenu.setText(cabecera.getNombre().trim());

        return view;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

}