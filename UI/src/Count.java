import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Count implements ActionListener {

    JFrame frame;
    JPanel panel;
    JLabel lcount;
    JTextField tcount;
    JButton bconfirm;

    Count()
    {
        frame=new JFrame("Create Quiz Page");
        panel=new JPanel();
        lcount=new JLabel("Enter Number of Questions : ");
        tcount=new JTextField(3);
        bconfirm=new JButton("Confirm");
        bconfirm.addActionListener(this);
        panel.add(lcount);
        panel.add(tcount);
        panel.add(bconfirm);
        frame.add(panel);
        frame.setSize(250,100);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new Count();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==bconfirm)
        {
            int count= Integer.parseInt(tcount.getText());
            for(int i=0;i<count;i++)
                new GenerateQuiz();
        }
        frame.dispose();
    }
}
