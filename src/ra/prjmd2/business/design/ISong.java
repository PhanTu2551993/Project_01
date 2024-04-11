package ra.prjmd2.business.design;

import ra.prjmd2.business.entity.Song;

public interface ISong extends IDesign{
    Song findById(int id);
    void searchSongByName();
}
