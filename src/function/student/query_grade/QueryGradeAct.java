package function.student.query_grade;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import component.MiniGradeRenderer;
import data.MiniGrade;
import data.user.Student;
import data.user.Teacher;
import user_service.UserService;
import util.JFrameUtilKt;
import util.StackFrame;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

/**
 * Created by qiao1 on 2017/1/3.
 */
public class QueryGradeAct extends StackFrame {
    private JPanel mPanelRoot;
    private JList<MiniGrade> mList;
    private JButton mBtnOk;

    private QueryGradePresenter mPresenter;

    public QueryGradeAct(QueryGradePresenter presenter) {
        super("成绩");
        this.mPresenter = presenter;
        setContentPane(mPanelRoot);
        JFrameUtilKt.init(this, 600, 800);
        initData();
        initEvent();
    }

    private void initData() {
        mList.setCellRenderer(new MiniGradeRenderer());
        showData(mPresenter.queryAllGrade(UserService.getInstance().getCurrentUser().getNo()));
    }

    private void showData(Vector<MiniGrade> grades) {
        mList.setListData(grades);
    }

    private void initEvent() {
        mBtnOk.addActionListener(l -> dispose());
    }

    public static void main(String[] args) {
        UserService.getInstance().signIn(new Student("1407064241", "李天龙", "五", "男"));
        UserService.getInstance().setUserType(UserService.TYPE_STUDENT);
        new QueryGradeAct(new QueryGradePresenter());
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        mPanelRoot = new JPanel();
        mPanelRoot.setLayout(new GridLayoutManager(1, 1, new Insets(10, 10, 10, 10), -1, -1));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new BorderLayout(0, 0));
        mPanelRoot.add(panel1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        mList = new JList();
        panel1.add(mList, BorderLayout.CENTER);
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(1, 3, new Insets(0, 0, 0, 0), -1, -1));
        panel1.add(panel2, BorderLayout.SOUTH);
        mBtnOk = new JButton();
        mBtnOk.setFont(new Font(mBtnOk.getFont().getName(), mBtnOk.getFont().getStyle(), 18));
        mBtnOk.setText("确认");
        panel2.add(mBtnOk, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        panel2.add(spacer1, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        panel2.add(spacer2, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mPanelRoot;
    }
}
