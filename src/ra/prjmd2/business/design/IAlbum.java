package ra.prjmd2.business.design;

import ra.prjmd2.business.entity.Album;

public interface IAlbum extends IDesign{
    Album findById(int inputIdUpdate);
    void searchAlbumByName();

}
