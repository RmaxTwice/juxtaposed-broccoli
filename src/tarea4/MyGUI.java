/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarea4;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.bytedeco.javacpp.opencv_core.*;
import org.bytedeco.javacv.CanvasFrame;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.bytedeco.javacv.OpenCVFrameConverter.ToIplImage;

/**
 *
 * @author Rafael Vasquez
 * @author Raquel Escalante
 */
public class MyGUI extends javax.swing.JFrame {

    // Global variables
    private String moviePath;
    private long lengthInTime;
    private final JFileChooser fcOpenMovie;
    private final JFileChooser fcOpenPic;
    private FFmpegFrameGrabber grabber;
    private BufferedImage targetImage;
    private final JLabel targetImageLabel;
    private final JLabel mosaicImageLabel;
    private int numberSamples;
    private int colsBlock;
    private int rowsBlock;
    private int featuresPerBlockHeight;
    private int featuresPerBlockWidth;
    private int distanceThreshold;
    private int[][] targetImageFeatures;
    private int[][] sampleImagesFeatures;
    
    /**
     * Creates new form MyGUI
     */
    public MyGUI() {
        initComponents();

        // Local variable
        // index of sample, boolean "used", r, g, b for each block.
        int numberFeatures = featuresPerBlockHeight * featuresPerBlockWidth * 3 + 2;

        // Global variables initizalization
        moviePath = "";
        lengthInTime = 0;
        fcOpenMovie = new JFileChooser();
        fcOpenPic = new JFileChooser();
        FileNameExtensionFilter moviesFilter = new FileNameExtensionFilter("Películas: *.mp4, *.wmv, *.mkv, *.avi, *.3gp, *.mov", "mp4", "avi", "mkv", "wmv", "3gp", "mov");
        FileNameExtensionFilter imagesFilter = new FileNameExtensionFilter("Imágenes: *.bmp, *.jpg, *.png", "bmp", "jpg", "png");
        fcOpenMovie.addChoosableFileFilter(moviesFilter);
        fcOpenPic.addChoosableFileFilter(imagesFilter);
        fcOpenMovie.setFileFilter(moviesFilter);
        fcOpenPic.setFileFilter(imagesFilter);
        grabber = new FFmpegFrameGrabber("");
        targetImage = null;
        targetImageLabel = new JLabel();
        mosaicImageLabel = new JLabel();
        numberSamples = 100;
        colsBlock = 8;
        rowsBlock = 8;
        featuresPerBlockHeight = 2;
        featuresPerBlockWidth = 2;
        distanceThreshold = 50;
        targetImageFeatures = new int[rowsBlock * colsBlock][numberFeatures];
        sampleImagesFeatures = new int[numberSamples][numberFeatures];
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        UbicacionPelicula = new javax.swing.JTextField();
        LabeUbicacionlPelicula = new javax.swing.JLabel();
        LabelImagenObjetivo = new javax.swing.JLabel();
        PanelImagenObjetivo = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        AbrirPelicula = new javax.swing.JToggleButton();
        ElegirImagen = new javax.swing.JButton();
        GenerarMuestras = new javax.swing.JToggleButton();
        GenerarMosaico = new javax.swing.JToggleButton();
        LabelMosaicoGenerado = new javax.swing.JLabel();
        PanelMosaicoGenerado = new javax.swing.JPanel();
        BarraMenu = new javax.swing.JMenuBar();
        MenuArchivo = new javax.swing.JMenu();
        GuardarMosaico = new javax.swing.JMenuItem();
        MenuPreferencias = new javax.swing.JMenu();
        Opciones = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Generador de Fotomosaicos | Rafael Vasquez & Raquel Escalante");

        UbicacionPelicula.setFocusable(false);

        LabeUbicacionlPelicula.setText("Ubicación de Película:");

        LabelImagenObjetivo.setText("Imagen Objetivo:");

        PanelImagenObjetivo.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout PanelImagenObjetivoLayout = new javax.swing.GroupLayout(PanelImagenObjetivo);
        PanelImagenObjetivo.setLayout(PanelImagenObjetivoLayout);
        PanelImagenObjetivoLayout.setHorizontalGroup(
            PanelImagenObjetivoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        PanelImagenObjetivoLayout.setVerticalGroup(
            PanelImagenObjetivoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 467, Short.MAX_VALUE)
        );

        AbrirPelicula.setText("1.Abrir Película");
        AbrirPelicula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AbrirPeliculaActionPerformed(evt);
            }
        });

        ElegirImagen.setText("2. Elegir Imagen");
        ElegirImagen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ElegirImagenActionPerformed(evt);
            }
        });

        GenerarMuestras.setText("3. Generar Muestras");
        GenerarMuestras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GenerarMuestrasActionPerformed(evt);
            }
        });

        GenerarMosaico.setText("4. Generar Mosaico");
        GenerarMosaico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GenerarMosaicoActionPerformed(evt);
            }
        });

        LabelMosaicoGenerado.setText("Foto-Mosaico Generado:");

        PanelMosaicoGenerado.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout PanelMosaicoGeneradoLayout = new javax.swing.GroupLayout(PanelMosaicoGenerado);
        PanelMosaicoGenerado.setLayout(PanelMosaicoGeneradoLayout);
        PanelMosaicoGeneradoLayout.setHorizontalGroup(
            PanelMosaicoGeneradoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 565, Short.MAX_VALUE)
        );
        PanelMosaicoGeneradoLayout.setVerticalGroup(
            PanelMosaicoGeneradoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        MenuArchivo.setText("Archivo");

        GuardarMosaico.setText("Guardar Mosaico");
        MenuArchivo.add(GuardarMosaico);

        BarraMenu.add(MenuArchivo);

        MenuPreferencias.setText("Preferencias");

        Opciones.setText("Opciones");
        MenuPreferencias.add(Opciones);

        BarraMenu.add(MenuPreferencias);

        setJMenuBar(BarraMenu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(AbrirPelicula, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(ElegirImagen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(33, 33, 33)
                        .addComponent(GenerarMuestras, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(GenerarMosaico, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(LabeUbicacionlPelicula)
                        .addGap(43, 43, 43)
                        .addComponent(UbicacionPelicula, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(131, 131, 131))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(LabelImagenObjetivo)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(PanelImagenObjetivo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PanelMosaicoGenerado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(LabelMosaicoGenerado))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(UbicacionPelicula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(LabeUbicacionlPelicula))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ElegirImagen)
                            .addComponent(GenerarMosaico)
                            .addComponent(AbrirPelicula)
                            .addComponent(GenerarMuestras))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(LabelImagenObjetivo)
                        .addGap(8, 8, 8)
                        .addComponent(PanelImagenObjetivo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(LabelMosaicoGenerado)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PanelMosaicoGenerado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private IplImage toIplImage(BufferedImage bufImage) {
        ToIplImage iplConverter = new OpenCVFrameConverter.ToIplImage();
        Java2DFrameConverter java2dConverter = new Java2DFrameConverter();
        IplImage iplImage = iplConverter.convert(java2dConverter.convert(bufImage));
        return iplImage;
}

    private void updateMoviePathGUI(String path){
        moviePath = path;
        UbicacionPelicula.setText(path);
    }

    private void updateTargetImageGUI(BufferedImage imgTg){
        int mWidth, mHeight;
        if(imgTg.getWidth() > imgTg.getHeight()){
            mWidth = jScrollPane1.getWidth() - 10;
            mHeight = (int)(((double)mWidth / (double)imgTg.getWidth()) * (double)imgTg.getHeight());
        }else{
            mHeight = jScrollPane1.getHeight() - 10;
            mWidth = (mHeight / imgTg.getHeight()) * imgTg.getWidth();
        }

        BufferedImage myResize = new BufferedImage(mWidth, mHeight, imgTg.getType());
        Graphics2D g = myResize.createGraphics();
        g.drawImage(imgTg, 0, 0, mWidth, mHeight, null);
        g.dispose();

        ImageIcon icon = new ImageIcon(myResize);
        // Adding the ImageIcon to the Label.
        targetImageLabel.setIcon( icon );
        //Aligning the image to the center.
        targetImageLabel.setHorizontalAlignment(JLabel.CENTER);
        //Adding the label to the Scrolling pane.
        jScrollPane1.getViewport().add(targetImageLabel);
        // Repainting the scroll pane to update the changes
        jScrollPane1.repaint();
    }

    private ArrayList divideTargetImage(BufferedImage target){
        ArrayList<BufferedImage> smallerTargetImages = new ArrayList();
        BufferedImage auxImg;
        int blockWidth = targetImage.getWidth() / colsBlock;
        int blockHeight = targetImage.getHeight() / rowsBlock;

        for (int i = 0; i < rowsBlock; i++){
            for (int j = 0; j < colsBlock; j++){
                auxImg = new BufferedImage(blockWidth, blockHeight, target.getType());
                smallerTargetImages.add(auxImg);

                Graphics2D gr = smallerTargetImages.get(i*colsBlock + j).createGraphics();
                gr.drawImage(target, 0, 0, blockWidth, blockHeight, blockWidth * j, blockHeight * i, blockWidth * j + blockWidth, blockHeight * i + blockHeight, null);
                gr.dispose();
            }
        }
//        try {
//            for (int i = 0; i < smallerTargetImages.size(); i++) {
//                ImageIO.write(smallerTargetImages.get(i), "jpg", new File("img" + i + ".jpg"));
//            }
//        } catch (IOException ex) {
//            Logger.getLogger(MyGUI.class.getName()).log(Level.SEVERE, null, ex);
//        }
        return smallerTargetImages;
    }

    private int[] extractFeaturesFromImage(BufferedImage img){
//        IplImage showImg = toIplImage(img);
//        CanvasFrame frame = new CanvasFrame("una imagen", CanvasFrame.getDefaultGamma()/grabber.getGamma());
//        OpenCVFrameConverter.ToIplImage converter = new OpenCVFrameConverter.ToIplImage();
//        Frame rotatedFrame = converter.convert(showImg);
//        frame.showImage(rotatedFrame);
        int featureWidth = img.getWidth() / featuresPerBlockWidth;
        int featureHeight = img.getHeight() / featuresPerBlockHeight;
        // index of sample image, boolean "used", r, g, b for each block.
        int featuresPerBlock = featuresPerBlockHeight * featuresPerBlockWidth * 3 + 2;
        int[] feature = new int[featuresPerBlock];
        int[] pixelData;
        int count = 0;

        feature[0] = feature[1] = 0;
        for (int i = 0; i < featuresPerBlockHeight; i++){
            for (int j = 0; j < featuresPerBlockWidth; j++){
                pixelData = new int[featureWidth * featureHeight];
                img.getRGB(featureWidth * j, featureHeight * i, featureWidth, featureHeight, pixelData, 0, featureWidth);
                pixelData = getAverageFeature(pixelData);
                feature[2 + count*3] = pixelData[0];
                feature[2 + count*3 + 1] = pixelData[1];
                feature[2 + count*3 + 2] = pixelData[2];
                count++;
            }
        }
        return feature;
    }

    private int[] getAverageFeature(int[] pixelData){
        int[] featuresArray = new int[3];
        long reds = 0;
        long greens = 0;
        long blues = 0;

        for(int i = 0; i < pixelData.length; i++){
            reds += (pixelData[i] >> 16) & 0xff;
            greens += (pixelData[i] >> 8) & 0xff;
            blues += pixelData[i] & 0xff;
        }
        featuresArray[0] = (int)reds / pixelData.length;
        featuresArray[1] = (int)greens / pixelData.length;
        featuresArray[2] = (int)blues / pixelData.length;

        return featuresArray;
    }

    private void generateSampleImagesFromMovie() throws FrameGrabber.Exception{
        double num = numberSamples + 1;
        double factor = 1/num;
        int lengthInVideoFrames = grabber.getLengthInVideoFrames();
        Frame frame;
        File outputfile;
        Java2DFrameConverter myFC = new Java2DFrameConverter();
        BufferedImage auxImage;
        //FileWriter fw = new FileWriter("IMAGEDB/image_features.txt");

        try(FileWriter fw = new FileWriter("IMAGEDB/0_image_features.txt")) {
            fw.write("#archivo de features de muestras" + System.lineSeparator());
        }catch (IOException ex) {
            Logger.getLogger(MyGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (int i = 0; i < num-1; i++){
            grabber.setVideoFrameNumber((int)(lengthInVideoFrames * factor) );
            frame = grabber.grab();
            //canvasFrame.showImage(frame);
            factor += 1/num;
            auxImage = myFC.convert(frame);

            // Extracting sample image features.
            sampleImagesFeatures[i] = extractFeaturesFromImage(auxImage);

            // Writing features to file

            // Writing images to file
            outputfile = new File("IMAGEDB/"+ i + ".jpg");
            try{
                ImageIO.write(auxImage, "jpg", outputfile);
            }
            catch (IOException e)
            {
                e.getMessage();
            }
        }
    }

    private void matchSampleImagesToTargetImage(){
        int minIndx = 0;
        long actualDifference;
        long minDifference;
        for(int tgImgIndx = 0; tgImgIndx < targetImageFeatures.length; tgImgIndx++){
            minDifference = 999999999;
            for(int smplImgIndx = 0; smplImgIndx < sampleImagesFeatures.length; smplImgIndx++){
                if (sampleImagesFeatures[smplImgIndx][1] == 0){
                    actualDifference = differenceBetweenFeaturesEuclidean(targetImageFeatures[tgImgIndx], sampleImagesFeatures[smplImgIndx]);
                    if(actualDifference < minDifference){
                        minDifference = actualDifference;
                        minIndx = smplImgIndx;
                    }
                }
            }
            targetImageFeatures[tgImgIndx][0] = minIndx;
            sampleImagesFeatures[minIndx][1] = 1;
        }
    }

    private long differenceBetweenFeaturesEuclidean(int[] targetFeatures, int[] sampleFeatures){
        //( USING EUCLIEAN DISTANCE )
        long result = 0;
        int singleDifference;

        for(int i = 2; i < targetFeatures.length; i++){
            singleDifference = targetFeatures[i] - sampleFeatures[i];
            result += singleDifference * singleDifference;
        }

        return result;
    }

    private void ElegirImagenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ElegirImagenActionPerformed
        if (!"".equals(moviePath)){
            int returnVal = fcOpenPic.showOpenDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fcOpenPic.getSelectedFile();
                try {
                    // Filling BufferedImage with file information
                    targetImage = ImageIO.read(file);
                    updateTargetImageGUI(targetImage);
                } catch (IOException e) {
                    // Report exceptions
                    JOptionPane.showMessageDialog(this, "Error al escoger Imagen Objetivo!");
                }
                divideTargetImage(targetImage);
            }
        }else{
            JOptionPane.showMessageDialog(this, "¡ERROR: Escoja una película primero!");
        }
    }//GEN-LAST:event_ElegirImagenActionPerformed

    private void AbrirPeliculaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AbrirPeliculaActionPerformed
       //In response to a button click, the file chooser is displayed
        int returnVal = fcOpenMovie.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            try {
                grabber.release();
                String path = fcOpenMovie.getSelectedFile().getAbsolutePath();
                updateMoviePathGUI(path);
                grabber = new FFmpegFrameGrabber(path);
                grabber.start();
            } catch (FrameGrabber.Exception ex) {
                Logger.getLogger(MyGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_AbrirPeliculaActionPerformed

    private void GenerarMosaicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GenerarMosaicoActionPerformed
        // 1- Target image gets divided into NxM Blocks and the features extraction of target image and sample images is performed.
        ArrayList<BufferedImage> targetImageBlocks = divideTargetImage(targetImage);
        double blockWidth = (double)targetImage.getWidth() / (double)colsBlock;
        double blockHeight = (double)targetImage.getHeight() / (double)rowsBlock;
        int[] pixelData;
        //targetImage.getRGB(0,0,10,10,pixelData,0,10);
        
        // Extracting features from targetImage's blocks
        for (int i = 0; i < targetImageBlocks.size(); i++){
            targetImageFeatures[i] = extractFeaturesFromImage(targetImageBlocks.get(i));
        }

//        pixelData[0] = pixelData[1] = 1;
        // 2- The sample images get matched to the target images blocks
        matchSampleImagesToTargetImage();

        pixelData = new int[2];
        // 3- Color Adjustment of sample images to improve quality.
    }//GEN-LAST:event_GenerarMosaicoActionPerformed

    private void GenerarMuestrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GenerarMuestrasActionPerformed
        // First find out if directory for samples exists:
        Path path = Paths.get("IMAGEDB/");
        if (Files.notExists(path)) {
            try {
                Files.createDirectory(path);
            } catch (IOException ex) {
                Logger.getLogger(MyGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        try {
            generateSampleImagesFromMovie();
        } catch (FrameGrabber.Exception ex) {
            Logger.getLogger(MyGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_GenerarMuestrasActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MyGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new MyGUI().setVisible(true);
        });
        
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton AbrirPelicula;
    private javax.swing.JMenuBar BarraMenu;
    private javax.swing.JButton ElegirImagen;
    private javax.swing.JToggleButton GenerarMosaico;
    private javax.swing.JToggleButton GenerarMuestras;
    private javax.swing.JMenuItem GuardarMosaico;
    private javax.swing.JLabel LabeUbicacionlPelicula;
    private javax.swing.JLabel LabelImagenObjetivo;
    private javax.swing.JLabel LabelMosaicoGenerado;
    private javax.swing.JMenu MenuArchivo;
    private javax.swing.JMenu MenuPreferencias;
    private javax.swing.JMenuItem Opciones;
    private javax.swing.JPanel PanelImagenObjetivo;
    private javax.swing.JPanel PanelMosaicoGenerado;
    private javax.swing.JTextField UbicacionPelicula;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
