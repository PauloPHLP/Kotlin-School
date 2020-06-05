package com.example.paulo.trabalhofinalandroid.activity.activity.professor;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.paulo.trabalhofinalandroid.R;
import com.example.paulo.trabalhofinalandroid.activity.activity.common.MainActivity;
import com.example.paulo.trabalhofinalandroid.activity.dao.ProfessorDao;
import com.example.paulo.trabalhofinalandroid.activity.model.Professor;

public class AdicionarProfessorActivity extends AppCompatActivity {

    //region Variables

    TextView edtTxtNome;
    TextView edtTxtData;
    TextView edtTxtNascimento;
    TextView edtTxtEndereco;
    TextView edtTextEmailProfessor;
    TextView edtTxtTelefoneProfessor;
    TextView edtTxtFormacoesProfessor;
    Button btnCadastrarProfessor;
    FloatingActionButton fltActBtnAddImageProfessor;
    ImageView crcImgViewProfilePicture;
    Professor professor, professorAlt;
    ProfessorDao professorDao;
    public static final int RESULT_LOAD_IMAGE = 12;

    //endregion

    //region onCreate method

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_professor);

        Intent intent = getIntent();
        professorAlt = (Professor) intent.getSerializableExtra("professor-enviado");
        professor = new Professor();
        professorDao = new ProfessorDao(this);

        edtTxtNome = findViewById(R.id.edtTxtNome);
        edtTxtData = findViewById(R.id.edtTxtData);
        edtTxtNascimento = findViewById(R.id.edtTxtNascimento);
        edtTxtEndereco = findViewById(R.id.edtTxtEndereco);
        edtTextEmailProfessor = findViewById(R.id.edtTextEmailProfessor);
        edtTxtTelefoneProfessor = findViewById(R.id.edtTxtTelefoneProfessor);
        edtTxtFormacoesProfessor = findViewById(R.id.edtTxtFormacoesProfessor);
        btnCadastrarProfessor = findViewById(R.id.btnCadastrarProfessor);
        fltActBtnAddImageProfessor = findViewById(R.id.fltActBtnAddImageProfessor);
        crcImgViewProfilePicture = findViewById(R.id.crcImgViewProfilePicture);

        fltActBtnAddImageProfessor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(
                        Intent.ACTION_GET_CONTENT, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                i.setType("image/*");
                startActivityForResult(i, RESULT_LOAD_IMAGE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_OK && resultCode == RESULT_LOAD_IMAGE) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();

            Bitmap bitmap = BitmapFactory.decodeFile(picturePath);
            crcImgViewProfilePicture.setImageBitmap(bitmap);
        }
    }

    //region onCreate method

    //region OnClick methods

    public void btnCancelarOnClick(View view) {
        edtTxtNome.setText("");
        edtTxtNascimento.setText("");
        edtTxtEndereco.setText("");
        edtTextEmailProfessor.setText("");
        edtTxtTelefoneProfessor.setText("");
        edtTxtFormacoesProfessor.setText("");
    }

    public void btnCadastrarProfessorOnClick(View view) {
        professor.setNome(edtTxtNome.getText().toString());
        professor.setDataDeNascimento(edtTxtData.getText().toString());
        professor.setLocalDeNascimento(edtTxtNascimento.getText().toString());
        professor.setEndereco(edtTxtEndereco.getText().toString());
        professor.setEmail(edtTextEmailProfessor.getText().toString());
        professor.setTelefone(edtTxtTelefoneProfessor.getText().toString());
        professor.setFormacoes(edtTxtFormacoesProfessor.getText().toString());

        professorDao.cadastrarProfessor(professor);

        notification(view);

        Toast.makeText(getApplicationContext(),"Professor cadastrado com sucesso!", Toast.LENGTH_LONG).show();

        finish();
    }

    //endregion

    public void notification(View view)
    {
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                new Intent(this, MainActivity.class), 0);

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.adicionar_professor)
                        .setContentTitle("Saat 9:00")
                        .setContentText("Mesai saatiniz başlamıştır Lütfen harakete geçiniz!");
        mBuilder.setContentIntent(contentIntent);
        mBuilder.setDefaults(Notification.DEFAULT_SOUND);
        mBuilder.setAutoCancel(true);
        NotificationManager mNotificationManager =
                (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(1, mBuilder.build());
    }
}