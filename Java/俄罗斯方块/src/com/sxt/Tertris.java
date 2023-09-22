package com.sxt;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Tertris extends JFrame implements KeyListener{
    File file1 = new File("图标.jpg");

    private static final int game_x = 26;//游戏的行数为26
    private static final int game_y = 12; //列数为12
    public static JTextArea[][] text;//文本域数组
    static int[][] data;//二维数组
    static JLabel label1;//显示游戏状态的标签
    static JLabel label;//显示游戏分数的标签
    static boolean isrunning;//用于判断游戏是否结束
    static int[] allRect; //用于存储所有的方块的数组

    static int rect;//用于存储当前方块的变量
    static Color color;//颜色
    static Color[] allColor;
    static int time=1000;//线程的休眠时间
    static int x;//表示方块坐标
    static int y;//表示方块坐标
    static int score = 0;//该变量用于计算得分
    static boolean game_pause = false;//定义一个标志变量,用于判断游戏是否暂停
    static int pause_times = 0;//定义一个变量用于记录按下暂停键的次数
    static int upbond ;
    static double rate;//速率

    //初始化窗口
    public void initWindow() {
        //设置上边框窗口
        this.setSize(600,850);//设置窗口大小
        this.setVisible(true);//设置窗口是否可见
        this.setLocationRelativeTo(null);//设置窗口居中
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//设置释放窗体
        this.setResizable(false);//设置窗口大小不可变
        this.setTitle("我的俄罗斯方块游戏");//设置标题
        this.setIconImage(new ImageIcon(file1.getAbsolutePath()).getImage());//设置图标图片
    }

    //初始化游戏界面
    public void initGamePanel() {
        JPanel game_main = new JPanel();
        //GridLayout是一个布局管理器，用于将组件以网格形式排列
        //将game_main面板的布局管理器设置为GridLayout，并指定了网格的行数、列数以及间距。
        game_main.setLayout(new GridLayout(game_x,game_y,1,1));//将game_main划分为26行，12列的1单位正方形方格
        //初始化面板
        for (int i = 0 ; i < text.length ; i++) {
            for (int j = 0 ; j < text[i].length ;j++) {
                text[i][j] = new JTextArea(game_x,game_y);//设置文本域的行列数,26行，12列
                text[i][j].setBackground(Color.WHITE);//设置文本域的背景颜色为白色
                text[i][j].addKeyListener(this);//添加键盘监听事件

                //初始化游戏边界
                if ((j == 0 && i != 3)|| (j == text[i].length-1 && i != 3) || i == text.length-1) {
                    text[i][j].setBackground(Color.BLACK);
                    data[i][j] = 1;
                }
                if(i == 3 && (j == 0 || j == text[i].length-1)){
                    text[i][j].setBackground(Color.BLUE);
                    data[i][j] = 1;
                }
                text[i][j].setEditable(false);//设置文本区域不可编辑
                game_main.add(text[i][j]);//文本区域添加到主面板上
            }
        }
        //添加到窗口中
        this.setLayout(new BorderLayout());
        this.add(game_main,BorderLayout.CENTER);
    }

    //初始化游戏的说明面板
    public void initExplainPanel() {
        JPanel explain_left = new JPanel();//创建游戏的左说明面板
        JPanel explain_right = new JPanel();//创建游戏的右说明面板

        explain_left.setLayout(new GridLayout(6,1));//左说明面板的位置
        explain_right.setLayout(new GridLayout(2,1));//右说明面板的位置

        //初始化左说明面板
        //在左说明面板,添加说明文字
        explain_left.add(new JLabel("当方块到达第四行游戏结束"));
        explain_left.add(new JLabel("  按左箭头，方块左移"));
        explain_left.add(new JLabel("  按右箭头，方块右移"));
        explain_left.add(new JLabel("  按下箭头，方块下落"));
        explain_left.add(new JLabel("  按下P键，游戏暂停"));
        explain_left.add(new JLabel("按空格键，方块逆时针旋转"));

        label1.setForeground(Color.RED);//设置标签的内容为红色字体
        explain_right.add(label);//把游戏状态标签添加到右说明面板
        explain_right.add(label1);//游戏分数标签添加到右说明面板
        this.add(explain_left,BorderLayout.WEST);//将左说明面板添加到窗口的左侧
        this.add(explain_right,BorderLayout.EAST);//将右说明面板添加到窗口的右侧
    }

    public Tertris() {
        text = new JTextArea[game_x][game_y];//game_x = 26,game_y = 12
        data = new int[game_x][game_y];
        label1 = new JLabel("游戏状态: 游戏中!");//初始化表示游戏状态的标签
        label = new JLabel("游戏得分为: " + score);//初始化表示游戏分数的标签
        initGamePanel();//调用游戏界面
        initExplainPanel();//调用说明面板
        initWindow();//调用窗口

        //用十六进制来储存所有方块的形状
        allRect = new int[]{
                0x0c88,0x008e,0x044c,0x00e2,0x04c8,0x00c6,0x0c44,
                0x00e8,0x088c,0x002e,0x08c4,0x006c,0x000f,0x8888,
                0x00cc,0x004e,0x04c4,0x00e4,0x08c8
        };
        /*0、0x0c88:1100   1、0x008e:1000  2、0x044c:0100  3、0x00e2:1110
        *           1000             1110            0100           0010
        *           1000             0000            1100           0000
        *           0000             0000            0000           0000
        *
        *4、0x04c8: 0000        5、0x00c6：0000      6、0x0c44：0000    7、0x00e8： 0000    8、0x088c：0000    9、0x002e： 0000    10、0x08c4：0000    11、0x006c：0000    12、0x000f：0000    13、0x8888：1000
        *           0100                   0000                1100                0000               1000               0000                1000                0000                0000                1000
        *           1100                   1100                0100                1110               1000               0010                1100                0110                0000                1000
        *           1000                   0110                0100                1000               1100               1110                0100                1100                1111                1000
         14、0x00cc：0000    15、0x004e：0000    16、0x04c4：0000    17、0x00e4：0000    18、0x08c8：0000
        *           0000                0000                0100                0000               1000
        *           1100                0100                1100                1110               1100
        *           1100                1110                0100                0100               1000
         */

        //储存方块所用的颜色
        allColor = new Color[]{
                Color.yellow,Color.GREEN,Color.BLUE,Color.CYAN,Color.ORANGE,Color.pink,Color.MAGENTA
        };
    }

    //开始游戏的方法
    public void game_begin() {
        //遍历整个网格，将已有方块的方格颜色改为黑色
        for(int i=0;i < 25;i++){
            for (int j=1;j < 11;j++){
                if(data[i][j] == 1)//如果data[i][j] == 1，则代表已经有方格
                    text[i][j].setBackground(Color.BLACK);
            }
        }
        while (isrunning) {
            //判断游戏是否结束

            //进行游戏
            game_run();
        }
        //在标签位置显示"游戏结束"
        label1.setText("游戏状态: 游戏结束!");
    }

    //随机生成下落方块形状的方法
    public void ranRect() {
        Random random1 = new Random();
        Random random2 = new Random();

        rect = allRect[random1.nextInt(19)];
        color = allColor[random2.nextInt(7)];
    }

    //游戏运行的方法
    public void game_run() {
        ranRect();//随机抽取方块的形状和颜色
        //方块下落位置
        x = 0;
        y = 5;

        //游戏过程
        for (int i = 0;i < game_x;i++) {
            try {
                Thread.sleep(time);//线程休眠1000毫秒

                //如果是暂停状态，i回调到原值
                if (game_pause) {
                    i--;
                } else {
                    //判断方块是否可以下落
                    if (!canFall(x,y)) {
                        changeData(x,y);//将data为1,表示有方块占用

                        //循环遍历4层,看是否有行可以消除，j代表行数
                        for (int j = x;j < x + 4;j++) {
                            int sum = 0;//sum表示每行中方格数

                            //k代表列数
                            for (int k = 1;k < game_y-1;k++) {
                                //如果data[j][k] == 1，代表有方块占用
                                if (data[j][k] == 1) {
                                    sum++;
                                }
                            }

                            //判断是否有一行可以被消除
                            if (sum == game_y-2) {
                                removeRow(j);//消除j这一行
                            }
                        }
                        //判断游戏是否失败
                        for (int j = 1;j < game_y-1;j++) {
                            //当方块到达第四行时，表示游戏结束
                            if (data[3][j] == 1) {
                                isrunning = false;
                                break;
                            }
                        }
                        break;
                    } else {
                        x++;//层数+1
                        fall(x,y);//方块下落一行
                    }
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //判断方块是否可以继续下落的方法
    public boolean canFall(int m,int n) {
        //定义一个变量
        int temp = 0x8000;//二进制1000 0000 0000
        //遍历4 * 4 方格
        for (int i = 0;i < 4;i++) {
            for (int j = 0;j < 4;j++) {
                /*按位与运算符（&）
                只有两个数的二进制同时为1，结果才为1，否则为0*/
                //如果(temp & rect) != 0，表示此位置有方块占用
                if ((temp & rect) != 0) {
                    //判断该位置的下一行是否有方块
                    if (data[m+1][n] == 1) {
                        return false;
                    }
                }
                n++;
                temp >>= 1;//temp右移一位
            }
            m++;
            n = n - 4;
        }
        //可以下落
        return true;
    }

    //改变不可下降的方块对应的区域的值的方法
    public void changeData(int m,int n) {
        //定义一个变量
        int temp = 0x8000;
        //遍历整个4 * 4的方块
        for (int i = 0;i < 4;i++) {
            for (int j = 0;j < 4;j++) {
                //如果(temp & rect) != 0，表示此位置有方块占用
                if ((temp & rect) != 0) {
                    data[m][n] = 1;//表示已有方块占用
                    text[m][n].setBackground(Color.BLACK);//将已经占用的方块颜色改为黑色
                }
                n++;
                temp >>= 1;//temp右移一位
            }
            m++;
            n = n - 4;
        }
    }

    //移除某一行的所有方块,令以上方块掉落的方法
    public void removeRow(int row) {
        int temp = 100;
        //将 row 这一行以上的所有数据赋值给其所处的下一行
        for (int i = row;i > 0;i--) {
            for (int j = 1;j < game_y-1;j++) {
                data[i][j] = data[i-1][j];
            }
        }
        //刷新游戏区域
        refresh(row);

        //方块加速
        if (time > upbond) {
            time -= temp*rate;
        }

        //分数规则，每一行100分
        score += temp;

        //显示变化后的分数
        label.setText("游戏得分为: " + score);
    }

    //刷新移除某一行后的游戏界面的方法
    public void refresh(int row) {
        //遍历row行以上的游戏区域
        for (int i = row;i > 0;i--) {
            for (int j = 1;j < game_y-1;j++) {
                //如果data[i][j] == 1，表示有方块占用，颜色改为黑色
                if (data[i][j] == 1) {
                    text[i][j].setBackground(Color.BLACK);
                }else {
                    text[i][j].setBackground(Color.WHITE);
                }
            }
        }
    }

    //方块向下掉落一层的方法
    public void fall(int m,int n) {
        if (m > 0) {
            clear(m-1,n);//清除上一层方块
        }
        //重新绘制方块
        draw(m,n);
    }

    //清除方块掉落后,上一层有颜色的地方的方法
    public void clear(int m,int n) {
        //定义变量
        int temp = 0x8000;
        for (int i = 0;i < 4;i++) {
            for (int j = 0;j < 4;j++) {
                if ((temp & rect) != 0) {
                    text[m][n].setBackground(Color.WHITE);//改变网格颜色
                }
                n++;
                temp >>= 1;
            }
            m++;
            n = n - 4;
        }
    }

    //重新绘制掉落后方块的方法
    public void draw(int m,int n) {
        //定义变量
        int temp = 0x8000;
        for (int i = 0;i < 4;i++) {
            for (int j = 0;j < 4;j++) {
                if ((temp & rect) != 0) {
                    text[m][n].setBackground(color);
                }
                n++;
                temp >>= 1;
            }
            m++;
            n = n - 4;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //控制游戏暂停
        if (e.getKeyChar() == 'p') {

            //判断游戏是否结束
            if (!isrunning) {
                return;
            }
            game_pause = true;
            label1.setText("游戏状态: 暂停中!");

            pause_times++;

            //判断按下一次,暂停游戏
            if (pause_times == 1) {
                game_pause = true;
                label1.setText("游戏: 暂停中!");
                pause p = new pause();
            }
        }

        //控制方块进行变形
        if (e.getKeyChar() == KeyEvent.VK_SPACE) {
            //判断游戏是否结束
            if (!isrunning) {
                return;
            }

            //判断游戏是否暂停
            if (game_pause) {
                return;
            }

            //定义变量,存储目前方块的索引
            int old;
            for (old = 0;old < allRect.length;old++) {
                //判断是否是当前方块
                if (rect == allRect[old]) {
                    break;
                }
            }

            //定义变量,存储变形后方块
            int next;

            //判断是方块
            if (old == 14) {
                return;
            }

            //清除当前方块
            clear(x,y);
            /*12、0000    13、0x8888：1000
                  0000                1000
                  0000                1000
                  1111                1000*/
            //I形状旋转
            if (old == 12 || old == 13) {
                next = allRect[old == 12 ? 13 : 12];

                if (canTurn(next,x,y)) {
                    rect = next;
                }
            }

            //L形状旋转
            if (old >= 0 && old <= 3) {
                next = allRect[old + 1 > 3 ? 0 : old + 1];

                if (canTurn(next,x,y)) {
                    rect = next;
                }
            }


            if (old == 4 || old == 5) {
                next = allRect[old == 4 ? 5 : 4];

                if (canTurn(next,x,y)) {
                    rect = next;
                }
            }

            if (old >= 6 && old <= 9) {
                next = allRect[old + 1 > 9 ? 6 : old + 1];

                if (canTurn(next,x,y)) {
                    rect = next;
                }
            }

            if (old == 10 || old == 11) {
                next = allRect[old == 10 ? 11 : 10];

                if (canTurn(next,x,y)) {
                    rect = next;
                }
            }

            if (old >= 15 && old <= 18) {
                next = allRect[old + 1 > 18 ? 15 : old + 1];

                if (canTurn(next,x,y)) {
                    rect = next;
                }
            }

            //重新绘制变形后方块
            draw(x,y);
        }
    }

    //判断方块此时是否可以变形的方法
    public boolean canTurn(int a,int m,int n) {
        //创建变量
        int temp = 0x8000;
        //遍历整个方块
        for (int i = 0;i < 4;i++) {
            for (int j = 0;j < 4;j++) {
                if ((a & temp) != 0) {
                    //如果已有方格，则无法旋转
                    if (data[m][n] == 1) {
                        return false;
                    }
                }
                n++;
                temp >>= 1;
            }
            m++;
            n = n - 4;
        }
        //可以变形
        return true;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //方块进行左移
        //37为左箭头
        if (e.getKeyCode() == 37) {
            //判断游戏是否结束
            if (!isrunning) {
                return;
            }

            //判断游戏是否暂停
            if (game_pause) {
                return;
            }

            //方块是否碰到左墙壁
            if (y <= 1) {
                return;
            }
            //定义一个变量
            int temp = 0x8000;
            //检查左侧是否有方块
            for (int i = x;i < x + 4;i++) {
                for (int j = y;j < y + 4;j++) {
                    if ((temp & rect) != 0) {
                        if (data[i][j-1] == 1) {
                            return;
                        }
                    }
                    temp >>= 1;
                }
            }

            clear(x,y);//首先清除目前方块
            y--;//列数左移一个单位
            draw(x,y);//重新绘制方块
        }

        //方块进行右移
        if (e.getKeyCode() == 39) {
            //判断游戏是否结束
            if (!isrunning) {
                return;
            }

            //判断游戏是否暂停
            if (game_pause) {
                return;
            }

            //判断是否碰到右墙壁
            if (y > (game_y-1)) {
                return;
            }

            //方块右移途中是否碰到别的方块
             int temp = 0x8000;
            for (int i = x;i < x + 4;i++) {
                for (int j = y;j < y + 4;j++) {
                    if ((temp & rect) != 0) {
                        if (data[i][j+1] == 1) {
                            return;
                        }
                    }
                    temp >>= 1;
                }
            }


            clear(x,y);//清除当前方块
            y++;
            draw(x,y);
        }

        //方块进行下落
        if (e.getKeyCode() == 40) {
            //判断游戏是否结束
            if (!isrunning) {
                return;
            }

            //判断游戏是否暂停
            if (game_pause) {
                return;
            }

            //判断方块是否可以下落
            if (!canFall(x,y)) {
                return;
            }

            clear(x,y);

            //改变方块的坐标
            x++;
            draw(x,y);//重新绘制方块
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
