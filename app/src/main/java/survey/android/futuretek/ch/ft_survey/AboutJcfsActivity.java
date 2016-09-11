package survey.android.futuretek.ch.ft_survey;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class AboutJcfsActivity extends BaseActivity {

    private String userName;
    private Button nextBtn;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jcfs);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.BLACK));

        nextBtn = (Button) findViewById(R.id.nextBtn);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                openInputDialog(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        EditText userInput = ((EditText) v.findViewById(R.id.userInput));
                        if (userInput != null && md5(userInput.getText().toString()).equals("c51ce410c124a10e0db5e4b97fc2af39")) {
                            getDatabase().cleanSkillsDb();
                            getDatabase().putSkill("Java, C/C++");
                            getDatabase().putSkill("Spring");
                            getDatabase().putSkill("Hibernate");
                            getDatabase().putSkill("JSF");
                            getDatabase().putSkill("PrimeFaces");
                            getDatabase().putSkill("JQuery");
                            getDatabase().putSkill("*SQL");
                            getDatabase().putSkill("Maven");
                            getDatabase().putSkill("SCRUM");

                            startActivity(new Intent(AboutJcfsActivity.this, SkillsActivity.class));
                        } else {
                            toast("Sorry that is not correct");
                        }
                    }
                });

            }
        });

    }

    protected void onResume() {
        super.onResume();
        nextBtn.setTextColor(Color.GRAY);
        nextBtn.setEnabled(false);
        ((ViewGroup) findViewById(R.id.textLayout)).removeAllViews();
        userName = getDatabase().get("usersName");
        List<String> textArray;

        textArray = new ArrayList<>();
        textArray.add("Welcome to the secret Jcfs Activity!");
        textArray.add("My goal is to try to impress you by fixing all the issues and developing all the optional requests.");

        textArray.add("Since you have been testing me this whole time, now it is the time for me to test you guys by giving you this cool riddle:");
        textArray.add("- - - - -");
        textArray.add("I have three children and the product of their ages is seventy–two. The sum of their ages is the number on this gate.");
        textArray.add("Sorry I can't tell you more right now, I have to see to my eldest child who is in bed with measles.");
        textArray.add("What is the number of the gate?");


        animateText(textArray, new AnimationListDone() {
            public void done() {
                Button nextBtn = ((Button) findViewById(R.id.nextBtn));
                nextBtn.setTextColor(Color.GREEN);
                nextBtn.setEnabled(true);
            }
        });
    }

    public static final String md5(final String s) {
        final String MD5 = "MD5";
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest.getInstance(MD5);
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuilder hexString = new StringBuilder();
            for (byte aMessageDigest : messageDigest) {
                String h = Integer.toHexString(0xFF & aMessageDigest);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            // oops
        }
        return "";
    }

}
