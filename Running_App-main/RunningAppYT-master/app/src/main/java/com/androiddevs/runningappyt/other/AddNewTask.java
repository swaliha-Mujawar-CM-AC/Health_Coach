package com.androiddevs.runningappyt.other;

import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.androiddevs.runningappyt.R;
import com.androiddevs.runningappyt.db.Meal;
import com.androiddevs.runningappyt.di.OnDialogCloseListener;
import com.androiddevs.runningappyt.utilities.DatabaseHelper;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class AddNewTask  extends BottomSheetDialogFragment {

    public static final String TAG = "ADD_NEW_MEAL";

    private EditText ed;
    private Button btn;

    private DatabaseHelper myDB;

    public static AddNewTask newInstance(){
        return new AddNewTask();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_new_meal,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ed = view.findViewById(R.id.add_meal_ed);
        btn = view.findViewById(R.id.save_btn);

        myDB = new DatabaseHelper(getActivity());

        boolean isUpdate = false;

        Bundle bundle = getArguments();
        if (bundle != null){
            isUpdate  = true;
            String task = bundle.getString("meal");
            ed.setText(task);

            if (task.length() > 0 ){
                btn.setEnabled(false);
            }

        }

        ed.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().equals("")){
                    btn.setEnabled(false);
                    btn.setBackgroundColor(Color.GRAY);
                }else {
                    btn.setEnabled(true);
                    btn.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        boolean finalIsUpdate = isUpdate;
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = ed.getText().toString();
                if (finalIsUpdate){
                    myDB.updateMeal(bundle.getInt("id"),text);
                }else {
                    Meal item = new Meal();
                    item.setMeal(text);
                    item.setStatus(0);
                    myDB.insertMeal(item);
                }
                dismiss();
            }
        });

    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);

        Activity activity = getActivity();
        if (activity instanceof OnDialogCloseListener){
            ((OnDialogCloseListener)activity).onDialogClose(dialog);
        }

    }
}
