import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.*;
import java.util.logging.Level;
import java.util.stream.Collectors;


public class main {


    private static List<LaiXe> laiXes = new ArrayList<>();
    private static List<Tuyen> tuyens = new ArrayList<>();
    private static List<BangPhanCong> bangPhanCongs = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc =new Scanner(System.in);
        System.out.println("Bus Driver Management");
        int choose;
        do {
            System.out.println("1.Nhập danh sách lái xe");
            System.out.println("2.Hiển thị danh sách lái xe");
            System.out.println("3.Nhập danh sách tuyến đường");
            System.out.println("4.Hiển thị danh sách tuyến đường");
            System.out.println("5.Nhập danh sách phân công");
            System.out.println("6.Hiển thị danh sách phân công");
            System.out.println("7.Sắp xếp theo họ tên lái xe");
            System.out.println("8.Sắp xếp theo tuyến đường (giảm dần)");
            System.out.println("9.Bảng thống kê khoảng cách chạy xe trong ngày của mỗi lái xe");
            System.out.println("10.Lưu danh sách  vào file");
            System.out.println("0.Thoát chương trình");
            System.out.println("Lựa chọn của bạn");
            choose = sc.nextInt();
            sc.nextLine();
            switch (choose){
                case 1:
                    nhapDanhSachLaiXe();
                    pause();
                    break;
                case 2:
                    hienThiDanhSachLaiXe();
                    pause();
                    break;
                case 3:
                    nhapDanhSachTuyenDuong();
                    pause();
                    break;
                case 4:
                    hienThiDanhSachTuyenDuong();
                    pause();
                    break;
                case 5:
                    nhapDanhSachPhanCong();
                    pause();
                    break;
                case 6:
                    hienThiDanhSachPhanCong();
                    pause();
                    break;
                case 7:
                    sapXepTheoTen();
                    pause();
                    break;
                case 8:
                    sapXepTheoTuyen();
                    pause();
                    break;
                case 9:
                    bangThongKeKhoangCachChayXe();
                    pause();
                    break;
                case 10:
                    luuDanhSachLaiXe();
                    luuDanhSachTuyen();
                    luuDanhSachBangPhanCong();
                    pause();
                    break;
                case 0:
                    System.out.println("Thoát...");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ vui lòng chọn lại");
                    break;
            }
        }while (choose !=0 );

        sc.close();
    }

    private static void nhapDanhSachLaiXe(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhập tên lái xe: ");
        String hoTen = sc.nextLine();
        System.out.println("Nhập địa chỉ: ");
        String diaChi = sc.nextLine();
        System.out.println("Nhập SDT: ");
        int SDT = sc.nextInt();
        sc.nextLine();
        System.out.println("Nhập trình độ(A->F): ");
        LaiXe.TrinhDo trinhDo = LaiXe.TrinhDo.valueOf(sc.nextLine().toUpperCase());
        LaiXe laiXe = new LaiXe(hoTen,diaChi,SDT,trinhDo);
        laiXes.add(laiXe);
    }

    public static void hienThiDanhSachLaiXe(){
        System.out.println("Danh sách lái xe");
        laiXes.forEach(System.out ::println);
    }

    private static void nhapDanhSachTuyenDuong(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhập Khoảng cách: ");
        int khoangCach = sc.nextInt();
        sc.nextLine();
        System.out.println("Nhập số điểm dừng: ");
        int soDiemDung = sc.nextInt();
        sc.nextLine();
        Tuyen tuyen = new Tuyen(khoangCach,soDiemDung);
        tuyens.add(tuyen);
    }

    public static void hienThiDanhSachTuyenDuong(){
        System.out.println("Danh sách tuyến đường" );
        tuyens.forEach(System.out::println);
    }

    private static void nhapDanhSachPhanCong(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhập vào id người lái: ");
        int idLaiXe = sc.nextInt();
        sc.nextLine();
        LaiXe laiXe = timKiemLaiXe(idLaiXe);
        if (laiXe == null){
            System.out.println("Không tìm thấy thông tin người lái");
            return;
        }

        System.out.println("Nhập vào id tuyến đường: ");
        int idTuyen = sc.nextInt();
        sc.nextLine();
        Tuyen tuyen = timKiemTuyen(idTuyen);
        if (tuyen == null){
            System.out.println("Không tìm thấy thông tin tuyến đường");
            return;
        }

        boolean check = bangPhanCongs.stream().anyMatch(b->b.getTuyen().equals(tuyens) && b.getLaiXe().equals(laiXe));
        if(check){
            System.out.println("Người lái xe được phân công đi trên tuyến đường này");
            return;
        }
        System.out.println("Nhập số lượt lái: ");
        int soLuot = sc.nextInt();
        sc.nextLine();

        int totals = bangPhanCongs.stream().filter(b->b.getLaiXe().equals(laiXe)).mapToInt(BangPhanCong::getSoLuot).sum();
        if (totals+soLuot>15){
            System.out.println("Tổng số lượt lái xe đã vượt quá số quy định trong ngày (15) !");
            return;
        }

        Map<Integer,String> tongLuotLai = new HashMap<Integer,String>();
        BangPhanCong bangPhanCong = new BangPhanCong(laiXe,tuyen,soLuot);
        bangPhanCongs.add(bangPhanCong);

    }

    private static LaiXe timKiemLaiXe(int idLaiXe){
        return laiXes.stream().filter(l->l.getMaLX()==idLaiXe).findFirst().orElse(null);
    }


    private static Tuyen timKiemTuyen(int idTuyen){
        return tuyens.stream().filter(t->t.getMaTuyen()==idTuyen).findFirst().orElse(null);
    }


    public static void hienThiDanhSachPhanCong(){
        System.out.println("Danh sách phân công");
        bangPhanCongs.forEach(System.out::println);
    }

    private static void sapXepTheoTen(){
        bangPhanCongs.sort(Comparator.comparing(l->l.getLaiXe().getHoTen()));
        System.out.println("Danh sách bảng phân công sau khi sắp xếp theo tên là:");
        hienThiDanhSachPhanCong();
    }

    private static void sapXepTheoTuyen(){
        System.out.println("Danh sách bảng phân công sau khi sắp xếp theo số lượng tuyến là:");
        bangPhanCongs.sort(Comparator.comparing(BangPhanCong::getSoLuot));
        hienThiDanhSachPhanCong();
    }

    private static void bangThongKeKhoangCachChayXe(){
        System.out.println("Bảng thống kê khoảng cách chạy trong ngày: ");
        Map<LaiXe,Integer> tongKhoangCach = bangPhanCongs.stream().collect(Collectors.groupingBy(BangPhanCong::getLaiXe,Collectors.summingInt(a->(int) (a.getTuyen().getKhoangCach() * a.getSoLuot()))));
        tongKhoangCach.forEach((laiXes, sum)->{
            System.out.println("Tài xế:"+laiXes.getHoTen()+" -Tổng quãng đường:"+sum+" km");
        });
    }

    private static void luuDanhSachLaiXe(){
        try (FileOutputStream fos = new FileOutputStream("laixe.txt");
             OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
             BufferedWriter bw = new BufferedWriter(osw)) {

            laiXes.forEach(laiXe -> {
                try{
                    String thongTinLaiXe = laiXe.toString();
                    bw.write(thongTinLaiXe);
                    bw.newLine();

                }catch (IOException e){
                    System.out.println("Lỗi khi lưu thông tin lái xe: " + e.getMessage());
                }
            });
            System.out.println("Danh sách lái xe đã được lưu vào file.");

        } catch (IOException e) {
            System.out.println("Lỗi khi lưu danh sách lái xe: " + e.getMessage());
        }
    }

    private static void luuDanhSachTuyen(){
        try (FileOutputStream fos = new FileOutputStream("tuyen.txt");
        OutputStreamWriter osw = new OutputStreamWriter(fos,"UTF-8");
        BufferedWriter bw = new BufferedWriter(osw)){
            tuyens.forEach(tuyen -> {
                try {
                    String thongTinTuyen =tuyen.toString();
                    bw.write(thongTinTuyen);
                    bw.newLine();
                }catch (IOException e){
                    System.out.println("Lỗi khi lưu thông tin tuyến: " + e.getMessage());
                }
            });
            System.out.println("Danh sách tuyến đã được lưu vào file.");
        }catch (IOException e){
            System.out.println("Lỗi khi lưu danh sách tuyến: " + e.getMessage());
        }
    }

    private static void luuDanhSachBangPhanCong(){
        try(FileOutputStream fos = new FileOutputStream("bangphancong.txt");
        OutputStreamWriter osw = new OutputStreamWriter(fos,"UTF-8");
        BufferedWriter bw = new BufferedWriter(osw)){
            bangPhanCongs.forEach(bangPhanCong -> {
                try {
                    String  thongTinBangPhanCong = bangPhanCongs.toString();
                    bw.write(thongTinBangPhanCong);
                    bw.newLine();
                }catch (IOException e){
                    System.out.println("Lỗi khi lưu danh sách bảng phân công: " + e.getMessage());
                }
            });
            System.out.println("Danh sách bảng phân công đã được lưu vào file");
        }catch (IOException e){
            System.out.println("Lỗi khi lưu danh sách bảng phân công: " + e.getMessage());
        }
    }

    private static void pause(){
        System.out.println("Nhấn enter để tiếp tục....");
        try{
            System.in.read();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

