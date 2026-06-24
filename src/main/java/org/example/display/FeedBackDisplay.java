package org.example.display;

import org.example.dao.FeedBackDAO;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FeedBackDisplay extends JFrame {
    public FeedBackDisplay (){
// --- CONFIGURAÇÕES DA JANELA DESKTOP TELA CHEIA ---
        setTitle("Enviar Feedback");
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Abre em tela cheia automaticamente
        setResizable(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        Color corFundoApp = new Color(248, 249, 250); // Off-white moderno

        // Painel Principal com os desenhos fluidos de fundo (identidade visual unificada)
        JPanel painelPrincipal = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Desenhos abstratos sutis para quebrar o visual "seco" nas laterais da tela
                g2.setColor(new Color(67, 97, 238, 14)); // Azul Royal translúcido
                g2.fillOval(-100, getHeight() - 400, 450, 450);

                g2.setColor(new Color(114, 9, 183, 10)); // Roxo translúcido
                g2.fillOval(getWidth() - 350, -100, 450, 450);

                g2.dispose();
            }
        };
        painelPrincipal.setBackground(corFundoApp);
        painelPrincipal.setBorder(new EmptyBorder(40, 40, 40, 40));

        // --- CARD CENTRALIZADO (Design de Plataforma Moderna) ---
        JPanel cardCentral = new JPanel(new BorderLayout(15, 20)) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Fundo do Card Branco
                g2.setColor(Color.WHITE);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 28, 28);

                // Linha de contorno fina e elegante
                g2.setColor(new Color(233, 236, 239));
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 28, 28);

                g2.dispose();
            }
        };
        cardCentral.setOpaque(false);
        cardCentral.setBorder(new EmptyBorder(45, 45, 40, 45)); // Margens internas do Card

        // --- CABEÇALHO DO CARD ---
        JPanel painelCabecalho = new JPanel();
        painelCabecalho.setLayout(new BoxLayout(painelCabecalho, BoxLayout.Y_AXIS));
        painelCabecalho.setOpaque(false);

        // Título imponente para Desktop
        JLabel titulo = new JLabel("Deixe seu Feedback");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 38));
        titulo.setForeground(new Color(33, 37, 41));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Subtítulo legível e bem espaçado
        JLabel subtitulo = new JLabel("Sua opinião é muito importante para nós!");
        subtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        subtitulo.setForeground(new Color(108, 117, 125));
        subtitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        subtitulo.setBorder(new EmptyBorder(6, 0, 20, 0));

        painelCabecalho.add(titulo);
        painelCabecalho.add(subtitulo);
        cardCentral.add(painelCabecalho, BorderLayout.NORTH);

        // --- ÁREA DE TEXTO EXPANDIDA (Centro) ---
        JTextArea textFeedBack = new JTextArea();
        textFeedBack.setFont(new Font("Segoe UI", Font.PLAIN, 16)); // Fonte maior para digitação confortável
        textFeedBack.setLineWrap(true);
        textFeedBack.setWrapStyleWord(true);
        textFeedBack.setBorder(new EmptyBorder(15, 15, 15, 15));

        JScrollPane scrollPane = new JScrollPane(textFeedBack);
        // Borda cinza suave arredondada para a caixa de texto
        scrollPane.setBorder(new LineBorder(new Color(222, 226, 230), 1, true));
        cardCentral.add(scrollPane, BorderLayout.CENTER);

        // --- PAINEL DO BOTÃO (Sul) ---
        JPanel painelBotao = new JPanel(new FlowLayout(FlowLayout.CENTER));
        painelBotao.setOpaque(false);
        painelBotao.setBorder(new EmptyBorder(20, 0, 0, 0));

        Color corBotao = new Color(67, 97, 238);
        Color corBotaoHover = new Color(72, 149, 239);

        // Botão maior e mais robusto acompanhando o padrão Desktop
        BotaoModerno btnSalvar = new BotaoModerno("Enviar Feedback", corBotao, corBotaoHover);
        btnSalvar.setFont(new Font("Segoe UI", Font.BOLD, 18));
        btnSalvar.setForeground(Color.WHITE);
        btnSalvar.setPreferredSize(new Dimension(240, 48));
        btnSalvar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // --- LÓGICA DO BOTÃO ---
        btnSalvar.addActionListener(e -> {
            String feedback = textFeedBack.getText().trim();

            if (feedback.isEmpty()) {
                mostrarMensagemModerna("Por favor, insira seu feedback antes de enviar.", "Aviso", new Color(255, 193, 7));
                return;
            }

            btnSalvar.setEnabled(false);
            btnSalvar.setText("Enviando...");

            new Thread(() -> {
                try {
                    Thread.sleep(700); // Efeito visual de carregamento rápido

                    // Exemplo de chamada do DAO (Descomente quando for usar)
                    // FeedBackDAO feedbackDAO = new FeedBackDAO();
                    // feedbackDAO.salvarFeedBack(feedback);

                    SwingUtilities.invokeLater(() -> {
                        mostrarMensagemModerna("Feedback enviado com sucesso!\nObrigado pela sua contribuição.", "Sucesso", new Color(46, 196, 182));
                        dispose();
                    });

                } catch (Exception ex) {
                    SwingUtilities.invokeLater(() -> {
                        btnSalvar.setEnabled(true);
                        btnSalvar.setText("Enviar Feedback");
                        mostrarMensagemModerna("Erro ao enviar feedback:\n" + ex.getMessage(), "Erro", new Color(230, 57, 70));
                    });
                }
            }).start();
        });

        painelBotao.add(btnSalvar);
        cardCentral.add(painelBotao, BorderLayout.SOUTH);

        // --- CENTRALIZADOR INTELIGENTE (GridBagLayout) ---
        JPanel centralizador = new JPanel(new GridBagLayout());
        centralizador.setOpaque(false);

        // Proporção ideal para dar uma excelente área de digitação no centro do monitor
        cardCentral.setPreferredSize(new Dimension(680, 520));
        centralizador.add(cardCentral);

        painelPrincipal.add(centralizador, BorderLayout.CENTER);
        add(painelPrincipal);
    }

    /**
     * MÉTODO DE AVISO CUSTOMIZADO
     */
    private void mostrarMensagemModerna(String mensagem, String titulo, Color corTema) {
        JDialog dialog = new JDialog(FeedBackDisplay.this, titulo, true);
        dialog.setResizable(false);

        JPanel painelDialog = new JPanel(new BorderLayout(15, 15));
        painelDialog.setBorder(new EmptyBorder(25, 30, 20, 30));
        painelDialog.setBackground(Color.WHITE);

        JLabel lblTitulo = new JLabel(titulo);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTitulo.setForeground(new Color(33, 37, 41));
        painelDialog.add(lblTitulo, BorderLayout.NORTH);

        JLabel lblMensagem = new JLabel("<html><body style='width: 280px; font-family: Segoe UI; font-size: 11pt; color: #495057;'>"
                + mensagem.replaceAll("\n", "<br>") + "</body></html>");
        painelDialog.add(lblMensagem, BorderLayout.CENTER);

        Color corHover = new Color(
                Math.min(corTema.getRed() + 25, 255),
                Math.min(corTema.getGreen() + 25, 255),
                Math.min(corTema.getBlue() + 25, 255)
        );

        BotaoModerno btnOk = new BotaoModerno("Ok", corTema, corHover);
        btnOk.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnOk.setForeground(Color.WHITE);
        btnOk.setPreferredSize(new Dimension(110, 35));
        btnOk.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnOk.addActionListener(ae -> dialog.dispose());

        JPanel painelBotao = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        painelBotao.setBackground(Color.WHITE);
        painelBotao.add(btnOk);
        painelDialog.add(painelBotao, BorderLayout.SOUTH);

        dialog.add(painelDialog);
        dialog.pack();
        dialog.setLocationRelativeTo(FeedBackDisplay.this);
        dialog.setVisible(true);
    }

    /**
     * CLASSE DO BOTÃO ARREDONDADO
     */
    class BotaoModerno extends JButton {
        private Color corAtual;
        private Color corBase;
        private Color corHover;
        private int raioBorda = 15; // Suavizado para combinar com o botão maior

        public BotaoModerno(String text, Color corBase, Color corHover) {
            super(text);
            this.corBase = corBase;
            this.corHover = corHover;
            this.corAtual = corBase;

            setContentAreaFilled(false);
            setFocusPainted(false);
            setBorderPainted(false);

            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    corAtual = corHover;
                    repaint();
                }
                @Override
                public void mouseExited(MouseEvent e) {
                    corAtual = corBase;
                    repaint();
                }
                @Override
                public void mousePressed(MouseEvent e) {
                    corAtual = corBase.darker();
                    repaint();
                }
                @Override
                public void mouseReleased(MouseEvent e) {
                    corAtual = corHover;
                    repaint();
                }
            });
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(corAtual);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), raioBorda, raioBorda);
            super.paintComponent(g);
            g2.dispose();
        }
    }
}
