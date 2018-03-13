package db;

import models.File;
import models.Folder;
import models.Owner;

import java.util.List;

public class Runner {

    public static void main(String[] args) {

        Owner owner1 = new Owner("David", "dav01");
        DBHelper.save(owner1);

        Owner owner2 = new Owner("Robert", "bob01");
        DBHelper.save(owner2);

        Folder folder1 = new Folder("Small Files", owner1);
        DBHelper.save(folder1);

        Folder folder2 = new Folder("Medium Files", owner2);
        DBHelper.save(folder2);

        Folder folder3 = new Folder("Large Files", owner1);
        DBHelper.save(folder3);

        File file1 = new File("FirstFile", ".jpg", 500, folder1);
        DBHelper.save(file1);

        File file2 = new File("SecondFile", ".doc", 400, folder1);
        DBHelper.save(file2);

        File file3 = new File("ThirdFile", ".txt", 800, folder1);
        DBHelper.save(file3);

        File file4 = new File("PictureFile", ".jpg", 50000, folder2);
        DBHelper.save(file4);

        File file5 = new File("VeryBigFile", ".mp4", 7000000, folder3);
        DBHelper.save(file5);

        File file6 = new File("TextFile", ".txt", 30000, folder2);
        DBHelper.save(file6);

        List<File> allFiles = DBHelper.getAll(File.class);
        List<Folder> allFolders = DBHelper.getAll(Folder.class);

        file6.setName("BigTextFile");
        file6.setSize(3000000);
        file6.setFolder(folder3);
        DBHelper.save(file6);

        List<File> allFilesAfterUpdate = DBHelper.getAll(File.class);

        File file7 = new File("TextFile", ".txt", 30000, folder2);
        DBHelper.save(file7);

        DBHelper.delete(file6);
        List<File> allFilesAfterDelete = DBHelper.getAll(File.class);

        List<File> allFilesInFolder2 = DBHelper.findFilesInFolder(folder2);

        List<Folder> allFoldersOwnedByOwner1 = DBHelper.findFoldersOwnedby(owner1);

    }
}
