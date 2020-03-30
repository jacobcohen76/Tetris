package tetris.view;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Color;

import tetris.model.BlockType;
import tetris.model.Tetromino;

/**
 *
 * @author Jacob Cohen
 */
public class StatisticsPanel extends javax.swing.JPanel {

	private static final long serialVersionUID = -7639703126943265256L;
	
	/**
     * Creates new form StatisticsPanel
     */
    public StatisticsPanel(Color background) {
    	setBackground(background);
    	
        tetrominoCountPanel_J = new TetrominoCountPanel(background);
        tetrominoCountPanel_T = new TetrominoCountPanel(background);
        tetrominoCountPanel_O = new TetrominoCountPanel(background);
        tetrominoCountPanel_Z = new TetrominoCountPanel(background);
        tetrominoCountPanel_L = new TetrominoCountPanel(background);
        tetrominoCountPanel_S = new TetrominoCountPanel(background);
        tetrominoCountPanel_I = new TetrominoCountPanel(background);
        
        tetrominoCountPanel_J.setTetromino(Tetromino.getPiece(BlockType.J));
        tetrominoCountPanel_T.setTetromino(Tetromino.getPiece(BlockType.T));
        tetrominoCountPanel_O.setTetromino(Tetromino.getPiece(BlockType.O));
        tetrominoCountPanel_Z.setTetromino(Tetromino.getPiece(BlockType.Z));
        tetrominoCountPanel_L.setTetromino(Tetromino.getPiece(BlockType.L));
        tetrominoCountPanel_S.setTetromino(Tetromino.getPiece(BlockType.S));
        tetrominoCountPanel_I.setTetromino(Tetromino.getPiece(BlockType.I));
        
        initComponents();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        statisticsLabel = new javax.swing.JLabel();

        setForeground(new java.awt.Color(255, 255, 255));

        statisticsLabel.setFont(new java.awt.Font("Consolas", 0, 24)); // NOI18N
        statisticsLabel.setForeground(new java.awt.Color(255, 255, 255));
        statisticsLabel.setText("STATISTICS");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(statisticsLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(tetrominoCountPanel_J, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tetrominoCountPanel_T, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tetrominoCountPanel_O, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tetrominoCountPanel_Z, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tetrominoCountPanel_L, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tetrominoCountPanel_S, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tetrominoCountPanel_I, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(statisticsLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tetrominoCountPanel_T, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tetrominoCountPanel_J, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tetrominoCountPanel_Z, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tetrominoCountPanel_O, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tetrominoCountPanel_S, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tetrominoCountPanel_L, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tetrominoCountPanel_I, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>                        


    // Variables declaration - do not modify                     
    private javax.swing.JLabel statisticsLabel;
    private TetrominoCountPanel tetrominoCountPanel_I;
    private TetrominoCountPanel tetrominoCountPanel_J;
    private TetrominoCountPanel tetrominoCountPanel_L;
    private TetrominoCountPanel tetrominoCountPanel_O;
    private TetrominoCountPanel tetrominoCountPanel_S;
    private TetrominoCountPanel tetrominoCountPanel_T;
    private TetrominoCountPanel tetrominoCountPanel_Z;
    // End of variables declaration                   
}