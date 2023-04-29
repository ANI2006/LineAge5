package com.example.lineage5;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.lineage5.ViewModel.RelationViewModel;
import com.example.lineage5.ViewModel.UserViewModel;
import com.example.lineage5.databinding.ActivityAddRelationBinding;
import com.example.lineage5.databinding.ActivityAddUserBinding;

public class AddRelationActivity extends AppCompatActivity {
    private ActivityAddRelationBinding binding;
    private String person1,person2,relation;

   // private String[] genders={" Male"," Female"};
    private RelationViewModel relationViewModel;
    private RelationUser relationUser;
    private boolean isEdit=false;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        binding= ActivityAddRelationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initDropDown();
        relationViewModel= ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(RelationViewModel.class);

        if (getIntent().hasExtra("model")){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                relationUser=getIntent().getParcelableExtra("model",RelationUser.class);
            }
            binding.edtFirstName.setText(projectModel.firstName);
            binding.edtLastName.setText(projectModel.lastName);
            binding.edtGender.setText(projectModel.gender);
            binding.edtAge.setText(String.valueOf(projectModel.age));
            binding.edtDescription.setText(projectModel.description);
            isEdit=true;


        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setShowHideAnimationEnabled(true);

        binding.btnAddUser.setOnClickListener(view -> {

            if(isEdit){
                firstName=binding.edtFirstName.getText().toString().trim();
                lastName=binding.edtLastName.getText().toString().trim();
                gender=binding.edtGender.getText().toString().trim();
                age=Integer.parseInt(binding.edtAge.getText().toString().trim());
                description=binding.edtDescription.getText().toString().trim();

                projectModel.firstName=firstName;
                projectModel.lastName=lastName;
                projectModel.gender=gender;
                projectModel.age=age;
                projectModel.description=description;

                userViewModel.updateUser(projectModel);
                Toast.makeText(this, "Updated", Toast.LENGTH_SHORT).show();

                finish();
            }else {
                firstName=binding.edtFirstName.getText().toString().trim();
                lastName=binding.edtLastName.getText().toString().trim();
                gender=binding.edtGender.getText().toString().trim();
                age=Integer.parseInt(binding.edtAge.getText().toString().trim());
                description=binding.edtDescription.getText().toString().trim();

                projectModel=new ProjectModel();
                projectModel.firstName=firstName;
                projectModel.lastName=lastName;
                projectModel.gender=gender;
                projectModel.age=age;
                projectModel.description=description;
                userViewModel.insertUser(projectModel);

                Toast.makeText(this, "Inserted", Toast.LENGTH_SHORT).show();


                finish();

            }

        });
    }

    @Override
    public boolean onSupportNavigateUp(){
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    private void initDropDown(){
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this, android.R.layout.select_dialog_item,genders);
        binding.edtGender.setAdapter(arrayAdapter);
        binding.edtGender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                gender=(String) adapterView.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

}
