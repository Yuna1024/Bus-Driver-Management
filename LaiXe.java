public class LaiXe {

    enum TrinhDo{
        A,B,C,D,E,F;
    }

    private static int nextID = 10000;
    private int maLX;
    private String hoTen;
    private String diaChi;
    private int SDT;
    private TrinhDo trinhDo;

    public  LaiXe(String hoTen, String diaChi, int SDT, TrinhDo trinhDo){
        this.maLX= nextID++;
        this.hoTen = hoTen;
        this.diaChi = diaChi;
        this.SDT = SDT;
        this.trinhDo = trinhDo;
    }

    public int getMaLX() {
        return maLX;
    }

    public void setMaLX(int maLX) {
        this.maLX = maLX;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public int getSDT() {
        return SDT;
    }

    public void setSDT(int SDT) {
        this.SDT = SDT;
    }

    public TrinhDo getTrinhDo() {
        return trinhDo;
    }

    public void setTrinhDo(TrinhDo trinhDo) {
        this.trinhDo = trinhDo;
    }

    @Override
    public String toString(){
        return "\nMã lái xe:  "+maLX+
                "\nHọ tên: " +hoTen+
                "\nĐịa chỉ: "+diaChi+
                "\nSDT: "+SDT+
                "\nTrình độ: "+trinhDo;
    }
}
