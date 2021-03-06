package com.example.tasks;

import android.app.DatePickerDialog;
import android.content.Context;
import android.text.InputType;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

import org.joda.time.LocalDate;

public class MyFunctions {

    public MyFunctions() {
    }

    public static void hideKeyboard(@NonNull Context context, @NonNull View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static String getDateText(int day, int month, int year) {
        String dateText = "";

        if (day < 10) dateText += "0" + day + "/";
        else dateText += day + "/";

        if (month < 10) dateText += "0" + month + "/" + year;
        else dateText += month + "/" + year;

        return dateText;
    }

    public static void setOnClickEtDateListener(Context context, @NonNull EditText etDate) {
        LocalDate currentDate = LocalDate.now();

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                context,
                (view, year, month, day) -> {
                    month += 1;
                    etDate.setText(getDateText(day, month, year));
                },
                currentDate.getYear(),
                currentDate.getMonthOfYear() - 1,
                currentDate.getDayOfMonth()
        );

        etDate.setOnClickListener(v -> {
            datePickerDialog.show();
            hideKeyboard(context, v);
        });
    }

    public static void setActionDoneButton(@NonNull EditText editText) {
        // Enables textMultiLine EditText with ActionDone button (without Enter button)
        editText.setImeOptions(EditorInfo.IME_ACTION_DONE);
        editText.setRawInputType(InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
    }

    public static boolean isNotEmpty(Context context, EditText et1) {
        if (et1.getText().toString().isEmpty()) {
            Toast.makeText(context, "Preencha os campos requeridos", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public static boolean isNotEmpty(Context context, EditText et1, EditText et2) {
        if (et1.getText().toString().isEmpty() || et2.getText().toString().isEmpty()) {
            Toast.makeText(context, "Preencha os campos requeridos", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public static boolean isNotEmpty(Context context, EditText et1, EditText et2, EditText et3) {
        if (et1.getText().toString().isEmpty() || et2.getText().toString().isEmpty() || et3.getText().toString().isEmpty()) {
            Toast.makeText(context, "Preencha os campos requeridos", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
