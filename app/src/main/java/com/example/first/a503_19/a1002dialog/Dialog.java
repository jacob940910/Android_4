package com.example.first.a503_19.a1002dialog;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

public class Dialog extends AppCompatActivity {

    private Button multiselect, customdialog, popupbtn;

    //선택여부를 판단 하기 위한 배열
    boolean [] mSelect = {false, false, false, false, false};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);

        multiselect = (Button)findViewById(R.id.multiselect);
        multiselect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(Dialog.this)
                        .setTitle("좋아하는 야구선수")
                        .setIcon(android.R.drawable.btn_plus)
                        .setMultiChoiceItems(R.array.player, mSelect, new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                //선택한 항목 위치의 값에 선택 여부를 저장
                                mSelect[which] = isChecked;
                            }
                        })
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //arrays.xml파일에 만든 문자열 배열 가져오기
                                String [] player = getResources().getStringArray(R.array.player);
                                //선택여부가 저장되어 있는 배열을 순회하면서 true일때 player의 데이터를 문자열에 추가
                                String result = "";
                                for(int i = 0; i < mSelect.length; i = i+1){
                                    if(mSelect[i]) {
                                        result = result + player[i] + "/t";
                                    }
                                }

                                //선택한 데이터 문자열을 토스트로 출력
                                Toast.makeText(Dialog.this,result,Toast.LENGTH_LONG)
                                        .show();
                            }
                        })
                        .setNegativeButton("취소",null)
                        .show();
            }
        });


        customdialog = (Button)findViewById(R.id.customdialog);
        customdialog.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                //레이아웃 파일의 내용불러오기
                //Anonymous 클래스에서 사용하기 위해서 final을 붙임
                //Anonymous 클래스에서는 자신을 포함하는 메소드의 지역변수를 사용할수 없습니다.
                //final은 가능합니다.
                final LinearLayout login = (LinearLayout)View.inflate(Dialog.this,R.layout.login,null);
                new AlertDialog.Builder(Dialog.this)
                        .setIcon(android.R.drawable.sym_def_app_icon)
                        .setTitle("로그인")
                        .setView(login).setNegativeButton("취소",null)
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                EditText id = (EditText)login.findViewById(R.id.id);
                                EditText password = (EditText)login.findViewById(R.id.password);
                                String result = "id:" + id.getText().toString() + "password:" + password.getText().toString();
                                Toast.makeText(Dialog.this, result, Toast.LENGTH_LONG);


                            }
                        })
                        .show();
            }
        });

        //popup.xml 내용을 가지는 뷰를 생성
        final LinearLayout popup = (LinearLayout)View.inflate(Dialog.this,R.layout.popup,null);

        final Button popupbtn = (Button)findViewById(R.id.popupwindow);
        popupbtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //팝업 윈도우
                final PopupWindow popupWindow = new PopupWindow(popup, 500, 200, true);
                //화면 출력 - 첫번째 매개변수는 기준 뷰
                //두번째와 세번쨰는 좌표 네번째 포커스 여부
                popupWindow.showAtLocation(popupbtn, Gravity.NO_GRAVITY, 100, 100);


                Button close = (Button)popup.findViewById(R.id.close);
                close.setOnClickListener(new View.OnClickListener(){

                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                });
            }
        });



    }


}
