package com.telefonica.first.tableroprueba;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.preference.ListPreference;
import android.preference.PreferenceManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

public class IconPickerPreference extends ListPreference  {

    private class CustomListPreferenceAdapter extends ArrayAdapter<IconItem> {

        private Context context;
        private List<IconItem> icons;
        private int resource;

        public CustomListPreferenceAdapter(Context context, int resource,
                                           List<IconItem> objects) {
            super(context, resource, objects);
            this.context = context;
            this.resource = resource;
            this.icons = objects;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            ViewHolder holder;

            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(resource, parent, false);

                holder = new ViewHolder();
                holder.iconName = (TextView) convertView
                        .findViewById(R.id.iconName);
                holder.iconImage = (ImageView) convertView
                        .findViewById(R.id.iconImage);
                holder.radioButton = (RadioButton) convertView
                        .findViewById(R.id.iconRadio);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.iconName.setText(icons.get(position).name);

            int identifier = context.getResources().getIdentifier(
                    icons.get(position).file, "drawable",
                    context.getPackageName());
            holder.iconImage.setImageResource(identifier);

            holder.radioButton.setChecked(icons.get(position).isChecked);

            convertView.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    ViewHolder holder = (ViewHolder) v.getTag();
                    for (int i = 0; i < icons.size(); i++) {
                        if (i == position)
                            icons.get(i).isChecked = true;
                        else
                            icons.get(i).isChecked = false;
                    }
                    getDialog().dismiss();
                }
            });

            return convertView;
        }

    }

    private static class IconItem {

        private String file;
        private boolean isChecked;
        private String name;

        public IconItem(CharSequence name, CharSequence file, boolean isChecked) {
            this(name.toString(), file.toString(), isChecked);
        }

        public IconItem(String name, String file, boolean isChecked) {
            this.name = name;
            this.file = file;
            this.isChecked = isChecked;
        }

    }

    private static class ViewHolder {
        protected ImageView iconImage;
        protected TextView iconName;
        protected RadioButton radioButton;
    }

    private Context context;
    private ImageView icon;

    private CharSequence[] iconFile;
    private CharSequence[] iconName;
    private List<IconItem> icons;
    private SharedPreferences preferences;
    private Resources resources;
    private String selectedIconFile, defaultIconFile;
    private TextView summary;

    public IconPickerPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;

        resources = context.getResources();
        preferences = PreferenceManager.getDefaultSharedPreferences(context);

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs,
                R.styleable.attrs_icon, 0, 0);

        try {
            defaultIconFile = a.getString(R.styleable.attrs_icon_iconFile);
        } finally {
            a.recycle();
        }
    }

    private String getEntry(String value) {
        String[] entries = resources.getStringArray(R.array.iconName);
        String[] values = resources.getStringArray(R.array.iconFile);
        int index = Arrays.asList(values).indexOf(value);
        return entries[index];
    }

    @Override
    protected void onBindView(View view) {
        super.onBindView(view);

        selectedIconFile = preferences.getString(
                resources.getString(R.string.custom_icon_key), defaultIconFile);

        icon = (ImageView) view.findViewById(R.id.iconSelected);
        updateIcon();

        summary = (TextView) view.findViewById(android.R.id.summary);
      /*  summary.setText(getEntry(selectedIconFile));*/

    }

    @Override
    protected void onDialogClosed(boolean positiveResult) {
        super.onDialogClosed(positiveResult);

        if (icons != null) {
            for (int i = 0; i < iconName.length; i++) {
                IconItem item = icons.get(i);
                if (item.isChecked) {

                    Editor editor = preferences.edit();
                    editor.putString(
                            resources.getString(R.string.custom_icon_key),
                            item.file);
                    editor.commit();

                    selectedIconFile = item.file;
                    updateIcon();

                    summary.setText(item.name);

                    break;
                }
            }
        }

    }

    @Override
    protected void onPrepareDialogBuilder(Builder builder) {

        builder.setNegativeButton("Cancelar", null);
        builder.setPositiveButton(null, null);

        iconName = getEntries();
        iconFile = getEntryValues();

        if (iconName == null || iconFile == null
                || iconName.length != iconFile.length) {
            throw new IllegalStateException(
                    "ListPreference Requiere un array de entradas "
                            + "y un array de valores con la misma longitud");
        }

        String selectedIcon = preferences.getString(
                resources.getString(R.string.custom_icon_key),
                resources.getString(R.string.icon_default));

        icons = new ArrayList<>();

        for (int i = 0; i < iconName.length; i++) {
            boolean isSelected = selectedIcon.equals(iconFile[i]);
            IconItem item = new IconItem(iconName[i], iconFile[i], isSelected);
            icons.add(item);
        }

        CustomListPreferenceAdapter customListPreferenceAdapter = new CustomListPreferenceAdapter(
                context, R.layout.item_picker, icons);
        builder.setAdapter(customListPreferenceAdapter, null);

    }

    private void updateIcon() {
        int identifier = resources.getIdentifier(selectedIconFile, "drawable",
                context.getPackageName());

        icon.setImageResource(identifier);
        icon.setTag(selectedIconFile);
    }

}