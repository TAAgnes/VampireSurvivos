package effect;

import jdk.jshell.spi.ExecutionControl;

import javax.imageio.ImageIO;
import javax.sound.sampled.Clip;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;


public class CacheDataLoader {
    private static CacheDataLoader instance = null;

    private File dataFolder = new File("data");
    private File framefile;
    private File animationfile;
    private File physmapfile;
    private File backgroundmapfile;
    private File soundfile;

    private Hashtable<String, FrameImage> frameImages;
    private Hashtable<String, Animation> animations;

    private Hashtable<String, Clip> sounds;

    private int[][] phys_map;
    private int[][] background_map;

    private CacheDataLoader() {
        framefile = new File(dataFolder,"Frame");
        animationfile = new File(dataFolder,"Animation");
        physmapfile = new File(dataFolder,"Map");
        backgroundmapfile = new File(dataFolder,"BackgroundMap");
        soundfile = new File(dataFolder,"Sound");
    }

    public static CacheDataLoader getInstance(){
        if(instance == null)
            instance = new CacheDataLoader();
        return instance;
    }

    //Đảm bảo chỉ về đúng một đối tượng duy nhất
    public FrameImage getFrameImage(String name){

        FrameImage frameImage = new FrameImage(instance.frameImages.get(name));
        return frameImage;

    }

    public File getFramefile() {
        return framefile;
    }

    public File getAnimationfile() {
        return animationfile;
    }

    public File getPhysmapfile() {
        return physmapfile;
    }

    public File getBackgroundmapfile() {
        return backgroundmapfile;
    }

    public void LoadData()throws IOException{

        LoadFrame();
        LoadAnimation();
        LoadPhysMap();
        LoadBackgroundMap();
        LoadSounds();

    }

    public void LoadPhysMap() throws IOException {}
    public void LoadBackgroundMap() throws IOException{}
    public void LoadSounds() throws IOException{}
    public void LoadFrame() throws IOException{
        frameImages = new Hashtable<String, FrameImage>();

        if(!framefile.exists() || !framefile.isDirectory()){
            throw new IOException("Frame file not found");
        }

        File[] frameFiles = framefile.listFiles();
        if(frameFiles == null){
            throw new IOException("Failed to list files in animation file");
        }

        for(File file : frameFiles){
            if(file.getName().endsWith(".txt")){
                FileReader fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr);

                String line = null;

                if(br.readLine() != null){
                    System.out.println("no data");
                    throw new IOException();
                } else {
                    fr = new FileReader(framefile);
                    br = new BufferedReader(fr);

                    while((line = br.readLine()).equals(""));
                    int n = Integer.parseInt(line);
                    String path = null;
                    BufferedImage imageData = null;
                    int j = 0;
                    for(int i=0;i<n;i++){
                       FrameImage frameImage = new FrameImage();
                       //Lay ten frame
                        while((line = br.readLine()).equals(""));
                        frameImage.setName(line);

                        //Lấy thông số tọa độ, chiều cao, độ dài
                        while((line = br.readLine()).equals(""));
                        String[] str = line.split(" ");

                        //kiểm tra xem hình ảnh này đã được đọc từ trước đó hay chưa
                        boolean refreshImage = (path == null || !path.equals(str[1]));
                        path = str[1];

                        //lấy tọa độ x của hình ảnh
                        while((line = br.readLine()).equals(""));
                        str = line.split(" ");
                        int x = Integer.parseInt(str[1]);

                        //lấy tọa độ y của hình ảnh
                        while((line = br.readLine()).equals(""));
                        str = line.split(" ");
                        int y = Integer.parseInt(str[1]);

                        //lấy độ rộng của hình ảnh
                        while((line = br.readLine()).equals(""));
                        str = line.split(" ");
                        int w = Integer.parseInt(str[1]);

                        //lấy độ cao của hình ảnh
                        while((line = br.readLine()).equals(""));
                        str = line.split(" ");
                        int h = Integer.parseInt(str[1]);

                        if(refreshImage) {
                            refreshImage = false;
                            imageData = ImageIO.read(new File(path));
                        }

                        if(imageData != null) {
                            BufferedImage image = imageData.getSubimage(x,y,w,h);
                            frameImage.setImage(image);
                        }

                        instance.frameImages.put(frameImage.getName(), frameImage);
                    }
                }
                br.close();
            }
        }
    }
    public void LoadAnimation() throws IOException {
        animations = new Hashtable<>();

        // Kiểm tra xem thư mục animation tồn tại không
        if (!animationfile.exists() || !animationfile.isDirectory()) {
            throw new IOException("Animation file not found");
        }

        // Lấy danh sách các file trong thư mục animation
        File[] animationFiles = animationfile.listFiles();
        if (animationFiles == null) {
            throw new IOException("Failed to list files in animation file");
        }

        // Lặp qua từng file trong thư mục animation
        for (File file : animationFiles) {
            // Kiểm tra xem file có phải là file animation.txt không
            if (file.getName().endsWith(".txt")) {
                FileReader fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr);

                String line = null;

                if (br.readLine() == null) {
                    System.out.println("No data");
                    throw new IOException();
                } else {
                    // Đọc số lượng animations

                    fr = new FileReader(animationfile);
                    br = new BufferedReader(fr);

                    while((line = br.readLine()).equals(""));
                    int n = Integer.parseInt(line);

                    for (int i = 0; i < n; i++) {
                        Animation animation = new Animation();

                        // Đọc tên animation
                        while ((line = br.readLine()).equals("")) ;
                        animation.setName(line);

                        // Đọc thông tin frame và thời gian hiển thị
                        while ((line = br.readLine()).equals("")) ;
                        String[] str = line.split(" ");
                        for (int j = 0; j < str.length; j += 2)
                            animation.add(getFrameImage(str[j]), Double.parseDouble(str[j + 1]));

                        animations.put(animation.getName(), animation);
                    }
                }

                br.close();
            }
        }
    }
}


