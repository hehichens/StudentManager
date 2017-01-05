package function.adminer.query_grade;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import component.QueryGradeRanderer;
import data.Grade;
import util.JFrameUtilKt;
import util.StackFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

/**
 * Created by qiao1 on 2017/1/1.
 */
public class QueryGradeAct extends StackFrame {

    private QueryGradePresenter mPresenter;
    private JPanel mPanelRoot;
    private JList<Grade> mList;
    private JButton mBtnSearch;
    private JTextField mTfContent;
    private JButton mBtnBack;
    private String key;

    private Vector<Grade> mData;

    public QueryGradeAct(QueryGradePresenter presenter) {
        super("查询或修改学生成绩");
        this.mPresenter = presenter;
        setContentPane(mPanelRoot);
        JFrameUtilKt.init(this, 600, 800);
        initEvent();
    }

    private void initEvent() {
        mList.setFixedCellWidth(getWidth());
        mList.setFixedCellHeight(getHeight() / 20);
        mList.setCellRenderer(new QueryGradeRanderer());
        mBtnBack.addActionListener(action -> dispose());
        mBtnSearch.addActionListener(action -> {
            key = mTfContent.getText();
            if (key != null && !key.trim().equals("")) {
                setData(mPresenter.search(key));
            } else {
                JOptionPane.showMessageDialog(this, "请输入内容后进行搜索！");
            }
        });
        mList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (mList.getSelectedIndex() != -1) {
                    if (e.getClickCount() == 2) {    //双击
                        if (mData != null) {
                            String new_score = JOptionPane.showInputDialog("请输入新的成绩：",
                                    mData.get(mList.getSelectedIndex()).getScore());
                            if (new_score == null || new_score.trim().equals(""))
                                return;
                            int result_code = mPresenter.changeScore(new_score,
                                    mData.get(mList.getSelectedIndex()).getStudentNo(),
                                    mData.get(mList.getSelectedIndex()).getCourseNo());
                            if (result_code == 0) {
                                JOptionPane.showMessageDialog(QueryGradeAct.this, "修改成功！");
                                setData(mPresenter.search(key));    //更新数据
                            } else {
                                JOptionPane.showMessageDialog(QueryGradeAct.this, "修改失败！");
                            }
                        }
                    }
                }
            }
        });
    }

    /**
     * 为列表项设置数据
     *
     * @param data
     */
    public void setData(Vector<Grade> data) {
        mData = data;
        mList.setListData(data);
    }

    @Override
    public void onDispose() {
        mPresenter.closeDB();
        super.onDispose();
    }

    public static void main(String[] args) {
        QueryGradePresenter presenter = new QueryGradePresenter();
        QueryGradeAct act = new QueryGradeAct(presenter);
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
        panel2.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel1.add(panel2, BorderLayout.NORTH);
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel3.setFont(new Font(panel3.getFont().getName(), panel3.getFont().getStyle(), 18));
        panel2.add(panel3, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setFont(new Font(label1.getFont().getName(), label1.getFont().getStyle(), 18));
        label1.setText("请输入学生姓名、老师姓名、学期、课程名称进行搜索：");
        panel3.add(label1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel2.add(panel4, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        mBtnSearch = new JButton();
        mBtnSearch.setFont(new Font(mBtnSearch.getFont().getName(), mBtnSearch.getFont().getStyle(), 18));
        mBtnSearch.setText("综合搜索");
        panel4.add(mBtnSearch, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        mTfContent = new JTextField();
        mTfContent.setFont(new Font(mTfContent.getFont().getName(), mTfContent.getFont().getStyle(), 18));
        mTfContent.setText("");
        panel4.add(mTfContent, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JPanel panel5 = new JPanel();
        panel5.setLayout(new GridLayoutManager(1, 3, new Insets(0, 0, 0, 0), -1, -1));
        panel1.add(panel5, BorderLayout.SOUTH);
        final Spacer spacer1 = new Spacer();
        panel5.add(spacer1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        panel5.add(spacer2, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        mBtnBack = new JButton();
        mBtnBack.setFont(new Font(mBtnBack.getFont().getName(), mBtnBack.getFont().getStyle(), 18));
        mBtnBack.setText("返回");
        panel5.add(mBtnBack, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        mList = new JList();
        panel1.add(mList, BorderLayout.WEST);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mPanelRoot;
    }
}
