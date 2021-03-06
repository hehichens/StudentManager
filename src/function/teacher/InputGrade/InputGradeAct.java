package function.teacher.InputGrade;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import component.MiniCourseRenderer;
import component.MiniGradeRenderer;
import data.MiniCourse;
import data.MiniGrade;
import data.user.Teacher;
import user_service.UserService;
import util.JFrameUtilKt;
import util.StackFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

/**
 * Created by qiao1 on 2017/1/3.
 */
public class InputGradeAct extends StackFrame {

    private JPanel mPanelRoot;
    private JList mList;
    private JButton mBtnSubmit;
    private JButton mBtnCancel;
    private JComboBox<MiniCourse> mCBCourse;

    private Vector<MiniCourse> courses;
    private Vector<MiniGrade> grades;

    private InputGradePresenter mPresenter;

    public InputGradeAct(InputGradePresenter presenter) {
        super("录入成绩");
        this.mPresenter = presenter;
        setContentPane(mPanelRoot);
        JFrameUtilKt.init(this, 450, 600);
        initData();
        initEvent();
    }

    private void initData() {
        mList.setCellRenderer(new MiniGradeRenderer());
        mCBCourse.setRenderer(new MiniCourseRenderer());
        courses = mPresenter.queryAllCourses(UserService.getInstance().getCurrentUser().getNo());
        showCourses(courses);
        course_no = courses.get(0).getNo();
        grades = mPresenter.queryGrades(course_no);
        showGrades(grades);
    }


    /**
     * 显示课程
     *
     * @param courses
     */
    private void showCourses(Vector<MiniCourse> courses) {
        courses.forEach(it -> {
            mCBCourse.addItem(it);
        });
    }

    private void showGrades(Vector<MiniGrade> grades) {
        mList.setListData(grades);
    }

    private String course_no = null;

    private void initEvent() {
        mBtnCancel.addActionListener(l -> dispose());
        mBtnSubmit.addActionListener(l -> {
            if (course_no != null) {
                if (mPresenter.updateGrade(grades, course_no) == 0) {
                    JOptionPane.showMessageDialog(this, "录入成绩成功！");
                } else {
                    JOptionPane.showMessageDialog(this, "录入成绩失败！");
                }
            }
        });
        mCBCourse.addItemListener(l -> {
            course_no = ((MiniCourse) l.getItem()).getNo();
            grades = mPresenter.queryGrades(course_no);
            showGrades(grades);
        });
        mList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (mList.getSelectedIndex() != -1) {
                    if (e.getClickCount() == 2) {
                        if (grades != null) {
                            String new_score = JOptionPane.showInputDialog("请输入新的成绩：",
                                    grades.get(mList.getSelectedIndex()).getScore());
                            grades.get(mList.getSelectedIndex()).setScore(new_score);
                        }
                    }
                }

            }
        });
    }

    public static void main(String[] args) {
        UserService.getInstance().signIn(new Teacher("1407064222", "乔云瑞", "男"));
        UserService.getInstance().setUserType(UserService.TYPE_TEACHER);
        new InputGradeAct(new InputGradePresenter());
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
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(1, 4, new Insets(0, 0, 0, 0), -1, -1));
        panel1.add(panel2, BorderLayout.SOUTH);
        mBtnSubmit = new JButton();
        mBtnSubmit.setFont(new Font(mBtnSubmit.getFont().getName(), mBtnSubmit.getFont().getStyle(), 18));
        mBtnSubmit.setText("提交");
        panel2.add(mBtnSubmit, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        mBtnCancel = new JButton();
        mBtnCancel.setFont(new Font(mBtnCancel.getFont().getName(), mBtnCancel.getFont().getStyle(), 18));
        mBtnCancel.setText("取消");
        panel2.add(mBtnCancel, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        panel2.add(spacer1, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        panel2.add(spacer2, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel1.add(panel3, BorderLayout.NORTH);
        mCBCourse = new JComboBox();
        mCBCourse.setFont(new Font(mCBCourse.getFont().getName(), mCBCourse.getFont().getStyle(), 18));
        panel3.add(mCBCourse, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setFont(new Font(label1.getFont().getName(), label1.getFont().getStyle(), 18));
        label1.setText("选择课程");
        panel3.add(label1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        mList = new JList();
        panel1.add(mList, BorderLayout.CENTER);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mPanelRoot;
    }
}
