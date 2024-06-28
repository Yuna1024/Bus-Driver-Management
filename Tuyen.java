public class Tuyen {
    private static int nextID = 100;
    private int maTuyen;
    private int khoangCach;
    private int soDiemDung;

    public Tuyen(int khoangCach,int soDiemDung){
        this.maTuyen = nextID++;
        this.khoangCach = khoangCach;
        this.soDiemDung = soDiemDung;
    }

    public int getMaTuyen() {
        return maTuyen;
    }

    public void setMaTuyen(int maTuyen) {
        this.maTuyen = maTuyen;
    }

    public int getKhoangCach() {
        return khoangCach;
    }

    public void setKhoangCach(int khoangCach) {
        this.khoangCach = khoangCach;
    }

    public int getSoDiemDung() {
        return soDiemDung;
    }

    public void setSoDiemDung(int soDiemDung) {
        this.soDiemDung = soDiemDung;
    }

    @Override
    public String toString(){
        return "\nMã tuyến: "+maTuyen+
                "\nKhoảng cách: " +khoangCach+ "km" +
                "\nSố điểm dừng: "+soDiemDung;
    }
}
