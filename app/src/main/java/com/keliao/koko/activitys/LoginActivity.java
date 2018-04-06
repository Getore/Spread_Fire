package com.keliao.koko.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.keliao.koko.db.UserData;
import com.keliao.koko.db.UserDataManager;
import com.keliao.koko.util.hideTitle;

public class LoginActivity extends hideTitle {

	private EditText mAccount;
	private EditText mPwd;
	private Button mRegisterButton;
	private Button mLoginButton;
	private Button mGorgetButton;
	private View loginView;
	private View loginSuccessView;
	private TextView loginSuccessShow;
	private UserDataManager mUserDataManager;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_activity);

		//用id找到控件
		mAccount = (EditText) findViewById(R.id.login_edit_account);
		mPwd = (EditText) findViewById(R.id.login_edit_pwd);
		mRegisterButton = (Button) findViewById(R.id.login_btn_register);
		mLoginButton = (Button) findViewById(R.id.login_btn_login);
		mGorgetButton = (Button) findViewById(R.id.login_btn_forget);
		loginView=findViewById(R.id.login_view);
		loginSuccessView=findViewById(R.id.login_success_view);
		loginSuccessShow=(TextView) findViewById(R.id.login_success_show);

		mRegisterButton.setOnClickListener(mListener);
		mLoginButton.setOnClickListener(mListener);
		mGorgetButton.setOnClickListener(mListener);
		//取消按钮 mCancleButton.setOnClickListener(mListener);
		
		if (mUserDataManager == null) {
			mUserDataManager = new UserDataManager(this);
			mUserDataManager.openDataBase();
        }

        //点击返回
		ImageButton backImageButton = (ImageButton)findViewById(R.id.backImageButton);
		backImageButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});

	}

	OnClickListener mListener = new OnClickListener() {
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.login_btn_register:
				register();
				break;
			case R.id.login_btn_login:
				login();
				break;
			case R.id.login_btn_forget:
				forget();
				break;
			}
		}
	};
	//点击登录按钮，进行逻辑判断
	public void login() {
		if (isUserNameAndPwdValid()) {
			String userName = mAccount.getText().toString().trim();
			String userPwd = mPwd.getText().toString().trim();
			int result = mUserDataManager.findUserByNameAndPwd(userName, userPwd);
			if(result==1){
				//login success
				//loginView.setVisibility(View.GONE);
				//loginSuccessView.setVisibility(View.VISIBLE);
				//loginSuccessShow.setText(getString(R.string.user_login_sucess, userName));

				Toast.makeText(this, getString(R.string.login_sucess),Toast.LENGTH_SHORT).show();
				mLoginButton.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						Intent intent_mLoginButton = new Intent(LoginActivity.this,MainActivity.class);
						//启动
						startActivity(intent_mLoginButton);
						finish();
					}
				});
			}else if(result==0){
				//login failed,user does't exist
				Toast.makeText(this, getString(R.string.login_fail),
						Toast.LENGTH_SHORT).show();
			}
		}
	}
	//新用户注册界面跳转
	public void register() {
		//实现注册的功能
		if (isUserNameAndPwdValid()) {
			String userName = mAccount.getText().toString().trim();
			String userPwd = mPwd.getText().toString().trim();
			//check if user name is already exist
			int count=mUserDataManager.findUserByName(userName);
			if(count>0){
				Toast.makeText(this, getString(R.string.name_already_exist, userName),
						Toast.LENGTH_SHORT).show();
				return ;
			}

			UserData mUser = new UserData(userName, userPwd);
			mUserDataManager.openDataBase();
			long flag = mUserDataManager.insertUserData(mUser);
			if (flag == -1) {
				Toast.makeText(this, getString(R.string.register_fail),
						Toast.LENGTH_SHORT).show();
			}else{
				Toast.makeText(this, getString(R.string.register_sucess),
						Toast.LENGTH_SHORT).show();
			}
		}
		//点击进行页面的跳转
		mRegisterButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent_mRegisterButton = new Intent(LoginActivity.this, RegisterActivity.class);
				//启动
				startActivity(intent_mRegisterButton);
			}
		});
	}

	//忘记密码界面跳转
	public void forget(){
		mGorgetButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent_mGorgetButton = new Intent(LoginActivity.this, ForgetPasswordActivity.class);
				//启动
				startActivity(intent_mGorgetButton);
			}
		});
	}

/*	public void cancle() {
		mAccount.setText("");
		mPwd.setText("");
	}*/

	public boolean isUserNameAndPwdValid() {
		if (mAccount.getText().toString().trim().equals("")) {
			Toast.makeText(this, getString(R.string.account_empty),
					Toast.LENGTH_SHORT).show();
			return false;
		} else if (mPwd.getText().toString().trim().equals("")) {
			Toast.makeText(this, getString(R.string.pwd_empty),
					Toast.LENGTH_SHORT).show();
			return false;
		}
		return true;
	}

	@Override
	protected void onResume() {
		super.onResume();
		if (mUserDataManager == null) {
			mUserDataManager = new UserDataManager(this);
			mUserDataManager.openDataBase();
        }
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	@Override
	protected void onPause() {
		super.onPause();
		if (mUserDataManager != null) {
			mUserDataManager.closeDataBase();
			mUserDataManager = null;
        }
	}

}