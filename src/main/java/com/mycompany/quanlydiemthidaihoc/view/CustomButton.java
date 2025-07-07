
package com.mycompany.quanlydiemthidaihoc.view;
import com.mycompany.quanlydiemthidaihoc.controller.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
/**
 *
 * @author OS
 */
public final class CustomButton extends JButton {

    private final Color normalColor = new Color(52, 152, 219); // Màu bình thường
    private final Color hoverColor = new Color(41, 128, 185);  // Màu hover
    private final Color clickColor = new Color(31, 97, 141);   // Màu khi click
    private final Color borderColor = new Color(255, 255, 255); // Viền
    private boolean hover;
    private boolean pressed;

    public CustomButton(String text) {
        super(text);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setForeground(Color.WHITE);
        setFont(new Font("Arial", Font.BOLD, 16));
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                hover = true;
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                hover = false;
                repaint();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                pressed = true;
                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                pressed = false;
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Color color = normalColor;
        if (pressed) {
            color = clickColor;
        } else if (hover) {
            color = hoverColor;
        }

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Đổ bóng nhẹ
        g2.setColor(new Color(0, 0, 0, 50));
        g2.fillRoundRect(5, 5, getWidth() - 10, getHeight() - 10, 30, 30);

        // Nền
        g2.setColor(color);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);

        // Viền
        g2.setColor(borderColor);
        g2.setStroke(new BasicStroke(2));
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 30, 30);

        super.paintComponent(g2);
        g2.dispose();
    }

    @Override
    public void setContentAreaFilled(boolean b) {
        // Vô hiệu hóa fill mặc định
    }
}