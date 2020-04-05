import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class QuizIndex implements ActionListener {
    JFrame frame;
    JPanel panel;
    JLabel label;
    JButton bgivequiz,bcreatequiz;

    QuizIndex()
    {
        frame=new JFrame("Quiz Index");
        panel=new JPanel();
        label=new JLabel("Welcome To Quizzzz !!! :)");
        bgivequiz=new JButton("Give Quiz");
        bcreatequiz=new JButton("Create Quiz");
        bcreatequiz.addActionListener(this);
        bgivequiz.addActionListener(this);
        panel.add(label);
        panel.add(bcreatequiz);
        panel.add(bgivequiz);
        frame.add(panel);
        frame.setSize(250,100);
        frame.setVisible(true);

    }

    public static void main(String[] args) {
        new QuizIndex();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==bcreatequiz)
        {
            frame.dispose();
            new Count();
        }
        if(e.getSource()==bgivequiz)
        {
            frame.dispose();
            try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
            int a=JOptionPane.showConfirmDialog(frame,"Are You Sure ? ");
            if(a==JOptionPane.YES_OPTION)
            {
                Connection con;
                try {
                    con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "vinithande4", "Swarali123");
                    System.out.println("Connection Succuess ....");
                    Statement stmt=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                    ResultSet rs=stmt.executeQuery("select * from quiz");
                    String que,optionA,optionB,optionC,optionD;
                    while (rs.next())
                    {
                        que=rs.getString(1);
                        optionA=rs.getString(2);
                        optionB=rs.getString(3);
                        optionC=rs.getString(4);
                        optionD=rs.getString(5);
                        if(rs.isFirst())
                            new GiveQuiz(que,optionA,optionB,optionC,optionD,1);
                        else
                            new GiveQuiz(que,optionA,optionB,optionC,optionD);

                    }
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

            }
        }

    }
}
