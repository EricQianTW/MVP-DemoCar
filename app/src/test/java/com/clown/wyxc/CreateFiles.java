package com.clown.wyxc;

import org.junit.Test;

import static com.clown.wyxc.Utils.createFile;
import static com.clown.wyxc.Utils.createWholeFile;

/**
 * Created by eric_shenn on 2017/5/2.
 */

public class CreateFiles {

    @Test
    public void acction() {
        String moduleStr = "Feedback";
        createFragmentMethod(moduleStr);
        createActivtyMethod(moduleStr);
        createContractMethod(moduleStr);
        createPresentMethod(moduleStr);
        createFragmentXmlMethod(moduleStr);
    }

    private void createFragmentMethod(String moduleStr) {

        String lowerModuleStr = moduleStr.toLowerCase();

        StringBuffer sbMethedFragment = new StringBuffer();

        sbMethedFragment.append("package com.clown.wyxc.x_" + lowerModuleStr + ";");
        sbMethedFragment.append("\n").append("");
        sbMethedFragment.append("\n").append("import android.os.Bundle;");
        sbMethedFragment.append("\n").append("import android.support.annotation.NonNull;");
        sbMethedFragment.append("\n").append("import android.support.annotation.Nullable;");
        sbMethedFragment.append("\n").append("import android.view.LayoutInflater;");
        sbMethedFragment.append("\n").append("import android.view.View;");
        sbMethedFragment.append("\n").append("import android.view.ViewGroup;");
        sbMethedFragment.append("\n").append("import android.widget.LinearLayout;");
        sbMethedFragment.append("\n").append("");
        sbMethedFragment.append("\n").append("import com.clown.wyxc.R;");
        sbMethedFragment.append("\n").append("import com.clown.wyxc.base.BaseFragment;");
        sbMethedFragment.append("\n").append("import com.clown.wyxc.scheme.ExceptionHandle;");
        sbMethedFragment.append("\n").append("import com.orhanobut.logger.Logger;");
        sbMethedFragment.append("\n").append("");
        sbMethedFragment.append("\n").append("import butterknife.Bind;");
        sbMethedFragment.append("\n").append("import butterknife.ButterKnife;");
        sbMethedFragment.append("\n").append("");
        sbMethedFragment.append("\n").append("import static com.google.common.base.Preconditions.checkNotNull;");
        sbMethedFragment.append("\n").append("");
        sbMethedFragment.append("\n").append("/**");
        sbMethedFragment.append("\n").append(" * Created by eric_qiantw on 16/4/20.");
        sbMethedFragment.append("\n").append(" */");
        sbMethedFragment.append("\n").append("public class " + moduleStr + "Fragment extends BaseFragment implements " + moduleStr + "Contract.View {");
        sbMethedFragment.append("\n").append("");
        sbMethedFragment.append("\n").append("    @Bind(R.id.ll_main)");
        sbMethedFragment.append("\n").append("    LinearLayout llMain;");
        sbMethedFragment.append("\n").append("");
        sbMethedFragment.append("\n").append("    private " + moduleStr + "Contract.Presenter mPresenter;");
        sbMethedFragment.append("\n").append("");
        sbMethedFragment.append("\n").append("    @Nullable");
        sbMethedFragment.append("\n").append("    @Override");
        sbMethedFragment.append("\n").append("    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {");
        sbMethedFragment.append("\n").append("        View view = inflater.inflate(R.layout."+lowerModuleStr+"_frg, container, false);");
        sbMethedFragment.append("\n").append("        ButterKnife.bind(this, view);");
        sbMethedFragment.append("\n").append("");
        sbMethedFragment.append("\n").append("        // 让Fragment中的onCreateOptionsMenu生效必须先调用setHasOptionsMenu方法");
        sbMethedFragment.append("\n").append("        setHasOptionsMenu(true);");
        sbMethedFragment.append("\n").append("        // 在 Activity 重绘时，Fragment 不会被重复绘制，也就是它会被“保留”");
        sbMethedFragment.append("\n").append("        setRetainInstance(true);");
        sbMethedFragment.append("\n").append("        return view;");
        sbMethedFragment.append("\n").append("    }");
        sbMethedFragment.append("\n").append("");
        sbMethedFragment.append("\n").append("    @Override");
        sbMethedFragment.append("\n").append("    public void onActivityCreated(Bundle savedInstanceState) {");
        sbMethedFragment.append("\n").append("        super.onActivityCreated(savedInstanceState);");
        sbMethedFragment.append("\n").append("");
        sbMethedFragment.append("\n").append("        try {");
        sbMethedFragment.append("\n").append("            initView();");
        sbMethedFragment.append("\n").append("            initAction();");
        sbMethedFragment.append("\n").append("        } catch (Exception e) {");
        sbMethedFragment.append("\n").append("            e.printStackTrace();");
        sbMethedFragment.append("\n").append("        }");
        sbMethedFragment.append("\n").append("    }");
        sbMethedFragment.append("\n").append("");
        sbMethedFragment.append("\n").append("    private void initView() throws Exception {");
        sbMethedFragment.append("\n").append("    }");
        sbMethedFragment.append("\n").append("");
        sbMethedFragment.append("\n").append("    @Override");
        sbMethedFragment.append("\n").append("    public void onResume() {");
        sbMethedFragment.append("\n").append("        super.onResume();");
        sbMethedFragment.append("\n").append("        mPresenter.start();");
        sbMethedFragment.append("\n").append("    }");
        sbMethedFragment.append("\n").append("");
        sbMethedFragment.append("\n").append("    @Override");
        sbMethedFragment.append("\n").append("    public void onDestroyView() {");
        sbMethedFragment.append("\n").append("        super.onDestroyView();");
        sbMethedFragment.append("\n").append("        ButterKnife.unbind(this);");
        sbMethedFragment.append("\n").append("    }");
        sbMethedFragment.append("\n").append("");
        sbMethedFragment.append("\n").append("    public "+moduleStr+"Fragment() {");
        sbMethedFragment.append("\n").append("        new "+moduleStr+"Presenter(this);");
        sbMethedFragment.append("\n").append("    }");
        sbMethedFragment.append("\n").append("");
        sbMethedFragment.append("\n").append("    public static "+moduleStr+"Fragment newInstance() {");
        sbMethedFragment.append("\n").append("        return new "+moduleStr+"Fragment();");
        sbMethedFragment.append("\n").append("    }");
        sbMethedFragment.append("\n").append("");
        sbMethedFragment.append("\n").append("    private void initAction() throws Exception {");
        sbMethedFragment.append("\n").append("");
        sbMethedFragment.append("\n").append("    }");
        sbMethedFragment.append("\n").append("");
        sbMethedFragment.append("\n").append("    @Override");
        sbMethedFragment.append("\n").append("    public void setPresenter(@NonNull "+moduleStr+"Contract.Presenter presenter) {");
        sbMethedFragment.append("\n").append("        mPresenter = checkNotNull(presenter);");
        sbMethedFragment.append("\n").append("    }");
        sbMethedFragment.append("\n").append("");
        sbMethedFragment.append("\n").append("    @Override");
        sbMethedFragment.append("\n").append("    public void showError(int errorCode, String errorMessage) {");
        sbMethedFragment.append("\n").append("        try {");
        sbMethedFragment.append("\n").append("            ExceptionHandle.handleError(getContext(), llMain, errorCode, errorMessage);");
        sbMethedFragment.append("\n").append("        } catch (Exception e) {");
        sbMethedFragment.append("\n").append("           Logger.e(e, TAG);");
        sbMethedFragment.append("\n").append("            e.printStackTrace();");
        sbMethedFragment.append("\n").append("        }");
        sbMethedFragment.append("\n").append("    }");
        sbMethedFragment.append("\n").append("");
        sbMethedFragment.append("\n").append("}");

        createFile(moduleStr + "Fragment",sbMethedFragment.toString());
    }

    private void createActivtyMethod(String moduleStr) {

        String lowerModuleStr = moduleStr.toLowerCase();

        StringBuffer sbMethed = new StringBuffer();

        sbMethed.append("package com.clown.wyxc.x_"+lowerModuleStr+";");
        sbMethed.append("\n").append("");
        sbMethed.append("\n").append("import android.os.Bundle;");
        sbMethed.append("\n").append("import android.widget.FrameLayout;");
        sbMethed.append("\n").append("");
        sbMethed.append("\n").append("import com.clown.wyxc.R;");
        sbMethed.append("\n").append("import com.clown.wyxc.base.BaseAppCompatActivity;");
        sbMethed.append("\n").append("import com.clown.wyxc.utils.ActivityUtils;");
        sbMethed.append("\n").append("");
        sbMethed.append("\n").append("import butterknife.Bind;");
        sbMethed.append("\n").append("import butterknife.ButterKnife;");
        sbMethed.append("\n").append("");
        sbMethed.append("\n").append("/**");
        sbMethed.append("\n").append(" * A login screen that offers login via email/password.");
        sbMethed.append("\n").append(" */");
        sbMethed.append("\n").append("public class "+moduleStr+"Activity extends BaseAppCompatActivity {");
        sbMethed.append("\n").append("");
        sbMethed.append("\n").append("    @Bind(R.id.contentFrame)");
        sbMethed.append("\n").append("    FrameLayout contentFrame;");
        sbMethed.append("\n").append("");
        sbMethed.append("\n").append("    @Override");
        sbMethed.append("\n").append("    protected void onCreate(Bundle savedInstanceState) {");
        sbMethed.append("\n").append("        super.onCreate(savedInstanceState);");
        sbMethed.append("\n").append("        setContentView(R.layout.x_common_act);");
        sbMethed.append("\n").append("        ButterKnife.bind(this);");
        sbMethed.append("\n").append("");
        sbMethed.append("\n").append("        try {");
        sbMethed.append("\n").append("            "+moduleStr+"Fragment "+lowerModuleStr+"Fragment = ("+moduleStr+"Fragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);");
        sbMethed.append("\n").append("");
        sbMethed.append("\n").append("            if ("+lowerModuleStr+"Fragment == null) {");
        sbMethed.append("\n").append("                "+lowerModuleStr+"Fragment = "+moduleStr+"Fragment.newInstance();");
        sbMethed.append("\n").append("");
        sbMethed.append("\n").append("                ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),");
        sbMethed.append("\n").append("                        "+lowerModuleStr+"Fragment, R.id.contentFrame);");
        sbMethed.append("\n").append("            }");
        sbMethed.append("\n").append("");
        sbMethed.append("\n").append("            // Create the presenter");
        sbMethed.append("\n").append("            new "+moduleStr+"Presenter("+lowerModuleStr+"Fragment);");
        sbMethed.append("\n").append("");
        sbMethed.append("\n").append("            initBack();");
        sbMethed.append("\n").append("        }catch (Exception e){");
        sbMethed.append("\n").append("            e.printStackTrace();");
        sbMethed.append("\n").append("        }");
        sbMethed.append("\n").append("    }");
        sbMethed.append("\n").append("");
        sbMethed.append("\n").append("    @Override");
        sbMethed.append("\n").append("    protected void onResume() {");
        sbMethed.append("\n").append("        super.onResume();");
        sbMethed.append("\n").append("    }");
        sbMethed.append("\n").append("");
        sbMethed.append("\n").append("    @Override");
        sbMethed.append("\n").append("    public void onBackPressed() {");
        sbMethed.append("\n").append("        super.onBackPressed();");
        sbMethed.append("\n").append("        finish();");
        sbMethed.append("\n").append("    }");
        sbMethed.append("\n").append("}");

        createFile(moduleStr + "Activity",sbMethed.toString());
    }

    private void createContractMethod(String moduleStr) {

        String lowerModuleStr = moduleStr.toLowerCase();

        StringBuffer sbMethed = new StringBuffer();

        sbMethed.append("package com.clown.wyxc.x_"+lowerModuleStr+";");
        sbMethed.append("\n").append("");
        sbMethed.append("\n").append("import com.clown.wyxc.base.BaseInterfacePresenter;");
        sbMethed.append("\n").append("import com.clown.wyxc.base.BaseInterfaceView;");
        sbMethed.append("\n").append("");
        sbMethed.append("\n").append("/**");
        sbMethed.append("\n").append(" * Created by eric_qiantw on 16/4/20.");
        sbMethed.append("\n").append(" */");
        sbMethed.append("\n").append("public interface "+moduleStr+"Contract {");
        sbMethed.append("\n").append("    interface View extends BaseInterfaceView<Presenter> {");
        sbMethed.append("\n").append("    }");
        sbMethed.append("\n").append("");
        sbMethed.append("\n").append("    interface Presenter extends BaseInterfacePresenter {");
        sbMethed.append("\n").append("    }");
        sbMethed.append("\n").append("}");

        createFile(moduleStr + "Contract",sbMethed.toString());
    }

    private void createPresentMethod(String moduleStr) {

        String lowerModuleStr = moduleStr.toLowerCase();

        StringBuffer sbMethed = new StringBuffer();

        sbMethed.append("package com.clown.wyxc.x_"+lowerModuleStr+";");
        sbMethed.append("\n").append("");
        sbMethed.append("\n").append("import android.support.annotation.NonNull;");
        sbMethed.append("\n").append("");
        sbMethed.append("\n").append("import com.clown.wyxc.base.BasePresenter;");
        sbMethed.append("\n").append("");
        sbMethed.append("\n").append("import static com.google.common.base.Preconditions.checkNotNull;");
        sbMethed.append("\n").append("");
        sbMethed.append("\n").append("/**");
        sbMethed.append("\n").append(" * Created by eric_qiantw on 16/4/20.");
        sbMethed.append("\n").append(" */");
        sbMethed.append("\n").append("public class "+moduleStr+"Presenter extends BasePresenter implements "+moduleStr+"Contract.Presenter{");
        sbMethed.append("\n").append("    private final "+moduleStr+"Contract.View mView;");
        sbMethed.append("\n").append("");
        sbMethed.append("\n").append("    public "+moduleStr+"Presenter(@NonNull "+moduleStr+"Contract.View loginView){");
        sbMethed.append("\n").append("        mView = checkNotNull(loginView, \"loginView be null!\");");
        sbMethed.append("\n").append("");
        sbMethed.append("\n").append("        mView.setPresenter(this);");
        sbMethed.append("\n").append("    }");
        sbMethed.append("\n").append("");
        sbMethed.append("\n").append("    @Override");
        sbMethed.append("\n").append("    public void start() {");
        sbMethed.append("\n").append("");
        sbMethed.append("\n").append("    }");
        sbMethed.append("\n").append("}");

        createFile(moduleStr + "Presenter",sbMethed.toString());
    }

    private void createFragmentXmlMethod(String moduleStr) {

        String lowerModuleStr = moduleStr.toLowerCase();

        StringBuffer sbMethed = new StringBuffer();

        sbMethed.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
        sbMethed.append("\n").append("<LinearLayout xmlns:android=\"http://schemas.android.com/apk/res/android\"");
        sbMethed.append("\n").append("    android:id=\"@+id/ll_main\"");
        sbMethed.append("\n").append("    android:layout_width=\"match_parent\"");
        sbMethed.append("\n").append("    android:layout_height=\"match_parent\"");
        sbMethed.append("\n").append("    android:orientation=\"vertical\">");
        sbMethed.append("\n").append("");
        sbMethed.append("\n").append("");
        sbMethed.append("\n").append("");
        sbMethed.append("\n").append("</LinearLayout>");

        createWholeFile(lowerModuleStr + "_frg.xml",sbMethed.toString());
    }
}
