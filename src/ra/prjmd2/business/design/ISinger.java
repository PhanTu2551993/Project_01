package ra.prjmd2.business.design;

import ra.prjmd2.business.entity.Album;
import ra.prjmd2.business.entity.Singer;

import java.util.Scanner;

public interface ISinger extends IDesign{
    Singer findById(int inputIdUpdate);
    void searchSingerByName();
}
