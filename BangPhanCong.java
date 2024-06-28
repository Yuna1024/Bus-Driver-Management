public class BangPhanCong {
    private LaiXe laiXe;
    private Tuyen tuyen;
    private int soLuot;

    public BangPhanCong(LaiXe laiXe, Tuyen tuyen,int soLuot){
        if (soLuot < 0 || soLuot >15){
            throw new IllegalArgumentException("Số lượt không hợp lệ (0<Số lượt < 15)");
        }
        this.laiXe=laiXe;
        this.tuyen=tuyen;
        this.soLuot=soLuot;
    }


    public LaiXe getLaiXe() {
        return laiXe;
    }

    public Tuyen getTuyen() {
        return tuyen;
    }

    public int getSoLuot() {
        return soLuot;
    }


    @Override
    public String toString() {
        return "\nHọ tên: " +getLaiXe().getHoTen()+
                "\nKhoảng cách: " +getTuyen().getKhoangCach()+
                "\nSố lượt: " +soLuot;
    }
}
