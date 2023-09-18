import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

// 导入需要用到的类

public class Calculator extends JFrame implements ActionListener {

    // 声明一个名为 Calculator 的类，继承自 JFrame 并实现 ActionListener 接口

    private JTextField resultField;
    // 声明一个名为 resultField 的文本框对象
    private String currentText = "";
    // 声明一个名为 currentText 的字符串类型变量，并赋值为空字符串
    private double currentNumber = 0.0;
    // 声明一个名为 currentNumber 的双精度浮点数类型变量，并赋值为 0
    private String currentOperator = "";
    // 声明一个名为 currentOperator 的字符串类型变量，并赋值为空字符串

    public Calculator() {
        setTitle("简易计算器");
        setSize(300, 250);
        setLocationRelativeTo(null);

        // 设置窗体的标题为“简易计算器”，大小为 300*250，屏幕居中显示

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(4, 4, 5, 5));

        // 新建一个 JPanel 对象 inputPanel，并将其排列方式设置为网格布局，每行 4 列，横向和纵向之间的间距均为 5

        JButton button1 = new JButton("1");
        button1.addActionListener(this);
        inputPanel.add(button1);

        JButton button2 = new JButton("2");
        button2.addActionListener(this);
        inputPanel.add(button2);

        JButton button3 = new JButton("3");
        button3.addActionListener(this);
        inputPanel.add(button3);

        JButton buttonPlus = new JButton("+");
        buttonPlus.addActionListener(this);
        inputPanel.add(buttonPlus);

        JButton button4 = new JButton("4");
        button4.addActionListener(this);
        inputPanel.add(button4);

        JButton button5 = new JButton("5");
        button5.addActionListener(this);
        inputPanel.add(button5);

        JButton button6 = new JButton("6");
        button6.addActionListener(this);
        inputPanel.add(button6);

        JButton buttonMinus = new JButton("-");
        buttonMinus.addActionListener(this);
        inputPanel.add(buttonMinus);

        JButton button7 = new JButton("7");
        button7.addActionListener(this);
        inputPanel.add(button7);

        JButton button8 = new JButton("8");
        button8.addActionListener(this);
        inputPanel.add(button8);

        JButton button9 = new JButton("9");
        button9.addActionListener(this);
        inputPanel.add(button9);

        JButton buttonMultiply = new JButton("*");
        buttonMultiply.addActionListener(this);
        inputPanel.add(buttonMultiply);

        JButton buttonClear = new JButton("C");
        buttonClear.addActionListener(this);
        inputPanel.add(buttonClear);

        JButton button0 = new JButton("0");
        button0.addActionListener(this);
        inputPanel.add(button0);

        JButton buttonEqual = new JButton("=");
        buttonEqual.addActionListener(this);
        inputPanel.add(buttonEqual);

        JButton buttonDivide = new JButton("/");
        buttonDivide.addActionListener(this);
        inputPanel.add(buttonDivide);

        // 新建 16 个按钮，添加到 inputPanel 中，并给每个按钮设置相应的文本，添加按钮的事件监听器

        JPanel resultPanel = new JPanel();
        resultPanel.setLayout(new FlowLayout());
        resultField = new JTextField(16);
        resultField.setEditable(false);
        resultPanel.add(resultField);

        // 新建一个 JPanel 对象 resultPanel，并将其排列方式设置为流式布局，将 resultField 添加到 resultPanel 中

        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(resultPanel, BorderLayout.NORTH);
        contentPane.add(inputPanel, BorderLayout.CENTER);

        // 获取当前窗体的内容面板对象 contentPane，将其排列方式设置为边框布局，将 resultPanel 和 inputPanel 添加到 contentPane 中

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    // 构造方法，创建窗口对象

    public void actionPerformed(ActionEvent e) {
        String buttonValue = e.getActionCommand();

        try {
            int number = Integer.parseInt(buttonValue);
            currentText += buttonValue;
            // 将输入的数字与当前字符串相加
            resultField.setText(currentText);
            // 在文本框中显示当前字符串
        }
        catch (NumberFormatException ex) {
            if (buttonValue.equals("+") || buttonValue.equals("-")
                    || buttonValue.equals("*") || buttonValue.equals("/")) {
                if (currentOperator.isEmpty()) {
                    currentNumber = Double.parseDouble(currentText);
                    // 如果当前操作符为空字符串，则将 currentText 转换成双精度浮点数并赋值给 currentNumber
                    currentText = "";
                    // 将 currentText 置为空字符串
                    currentOperator = buttonValue;
                    // 保存当前操作符
                }
                else {
                    double secondNumber = Double.parseDouble(currentText);
                    // 如果当前操作符不为空字符串，则将 currentText 转换成双精度浮点数并赋值给 secondNumber
                    currentText = "";
                    // 将 currentText 置为空字符串
                    switch (currentOperator) {
                        case "+":
                            currentNumber += secondNumber;
                            break;
                        case "-":
                            currentNumber -= secondNumber;
                            break;
                        case "*":
                            currentNumber *= secondNumber;
                            break;
                        case "/":
                            currentNumber /= secondNumber;
                            break;
                    }
                    currentOperator = buttonValue;
                    // 保存当前操作符
                }
            }
            else if (buttonValue.equals("=")) {
                double secondNumber = Double.parseDouble(currentText);
                currentText = "";
                // 将 currentText 置为空字符串
                switch (currentOperator) {
                    case "+":
                        currentNumber += secondNumber;
                        break;
                    case "-":
                        currentNumber -= secondNumber;
                        break;
                    case "*":
                        currentNumber *= secondNumber;
                        break;
                    case "/":
                        currentNumber /= secondNumber;
                        break;
                }
                resultField.setText(Double.toString(currentNumber));
                // 在文本框中显示计算结果
                currentNumber = 0.0;
                // 将 currentNumber 置为 0
                currentOperator = "";
                // 将 currentOperator 置为空字符串，表示已完成一次运算
            }
            else if (buttonValue.equals("C")) {
                currentText = "";
                currentNumber = 0.0;
                currentOperator = "";
                resultField.setText("");
                // 将所有的变量都恢复到初始状态，清空文本框中的内容
            }
        }
    }

    // 监听器方法，当按钮被点击时触发

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        // 创建 Calculator 对象
        calculator.setSize(300, 400);
        // 设置程序大小
        calculator.setTitle("计算器");
        // 设置程序标题
        calculator.setLocationRelativeTo(null);
        // 设置程序居中是否�
        calculator.setVisible(true);
        // 设置程序可否被是否�
        calculator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 设置程序允讯否被其他程序共使用
        calculator.setResizable(false);
        // 设置程序是否可否被收立�
        //calculator.setAlwaysOnTop(true);


        //冒泡排序







    }

}

// 主方法，创建 Calculator 对象


//dsf






