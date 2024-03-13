package PandaZooData;
import java.io.*;
import java.util.ArrayList;

public class PandaZoo {  // сделать сериализацию
    protected ArrayList<Pandable> list = new ArrayList<>();

   /* public PandaZoo(){
        list.add(new BigPanda("zxbmxzv"));
        list.add(new SmallPanda("ksjvn"));
        list.add(new PandaAnt("bk","hbkb"));
        list.add(new PandaFish("kjdv"));
    }*/

    public void addPanda(Mammal panda){
        list.add(panda);
    }
    public void addPanda(Fish panda){
        list.add(panda);
    }
    public void addPanda(Ant panda){
        list.add(panda);
    }
    public void removePanda(int index){list.remove(index);}

    // для 5 лабы
    public int sizeZoo () {return list.size();}
    public Pandable getPanda(int index) {return list.get(index);}

    public void outputPanda(){ 
        int i=0;
        for (Pandable panda: list)
        {
            System.out.println(i+" "+panda.getName());
            i++;
        }
    }
    public void getMemberDescription(int index)
    {
        list.get(index).describe_panda();
    }
    public void getMemberPhoto(int index) {list.get(index).getPhotos();}

    public void makeMemberHunt()
    {
        int i=0;
        for (Pandable panda: list) {
            if (panda instanceof Mammal) {
                System.out.println(panda.getName() + " охотится");
                ((Mammal) panda).hunt();
            }
            i++;
        }
    }
    public void makeMemberSwim()
    {
        int i=0;
        for (Pandable panda: list) {
            if (panda instanceof Fish) {
                System.out.println(panda.getName() + " плавает");
                ((Fish) panda).swim();
            }
            i++;
        }
    }
    public void makeMemberBuildAntHill()
    {
        int i=0;
        for (Pandable panda: list) {
            if (panda instanceof Ant) {
                System.out.println(panda.getName() + " строит муравейник");
                ((Ant) panda).buildAnthill();
            }
            i++;
        }
    }
    public void readFromFile(String path) throws ClassNotFoundException, IOException
    {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path));
        int amount = ois.readInt();
        int c;
        for (int i = 0; i < amount; i++) {
            c=ois.readInt();
            if (c==0)
                list.add((BigPanda)ois.readObject());
            else if (c==1)
                list.add((SmallPanda)ois.readObject());
            else if (c==2)
                list.add((PandaFish)ois.readObject());
            else
                list.add((PandaAnt)ois.readObject());
        }
    }

    public void writeToFile(String path) throws IOException, ZooIsEmpty {
        if (!list.isEmpty()) {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path));
            oos.writeInt(list.size());
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i) instanceof BigPanda) {
                    oos.writeInt(0);
                    oos.writeObject((BigPanda) list.get(i));
                } else if (list.get(i) instanceof SmallPanda) {
                    oos.writeInt(1);
                    oos.writeObject((SmallPanda) list.get(i));
                } else if (list.get(i) instanceof PandaFish) {
                    oos.writeInt(2);
                    oos.writeObject((PandaFish) list.get(i));
                } else {
                    oos.writeInt(3);
                    oos.writeObject((PandaAnt) list.get(i));
                }
            }
        }
        else
            throw new ZooIsEmpty("В зоопарке нет панд!");
    }
    public void writeMemberToFile(String path, int id) throws IOException
    {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path));
        oos.writeInt(1);
        if (list.get(id) instanceof BigPanda)
        {
            oos.writeInt(0);
            oos.writeObject((BigPanda)list.get(id));
        }
        else if (list.get(id) instanceof SmallPanda)
        {
            oos.writeInt(1);
            oos.writeObject((SmallPanda)list.get(id));
        }
        else if (list.get(id) instanceof PandaFish)
        {
            oos.writeInt(2);
            oos.writeObject((PandaFish)list.get(id));
        }
        else
        {
            oos.writeInt(3);
            oos.writeObject((PandaAnt)list.get(id));
        }
    }
}
