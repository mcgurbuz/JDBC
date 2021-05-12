import java.sql.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws SQLException {
        selectDemo();
    }

    public static void selectDemo() throws SQLException {
        Connection connection = null;
        DBHelper helper = new DBHelper();
        Statement statement = null; //Bu sorgular SQL STATEMENT diye geçer JDBC de karşlığı statamentdır.
        //Statement oluşturduk.SQL cümleciğimizle ilgili işlemleri yapıcak
        ResultSet resultSet;  //Sonuçlar topluluğu Sorgu sonucu gelen datalar Resultset olarak karşılanıyor.
        try {
            connection = helper.getConnection();
            System.out.println("Bağlantı kuruldu...");
            statement = connection.createStatement(); //Gönderilecek sorgu bu bağlantıya gidecek diyoruz.
            resultSet = statement.executeQuery("select Code,Name,Continent,Region from country ");
            //Bu SQL cümleciğini çalıştır.
            //Gelen cevapları bir nesne arrayine atayıp resultset.next ile iterate edip tek tek hepsini çektik.
            ArrayList<Country> countries = new ArrayList<Country>(); //Country tipinde countries Arraylisti oluşturdum.
            //Arraylisti while dışında yaptığınıza emin olun diğer türlü her seferinde döngüde dönecek ve her defasında yenilenecektir.
            while (resultSet.next()) { //Tek tek iterate et gez demek.
                // System.out.println(resultSet.getString("Name"));
                //Veri tabanı türü neyse ona göre çekiyoruz. Örneğin Name String olduğu için String olarak
                //çekiyoruz
                countries.add(new Country(resultSet.getString("Code"),
                        resultSet.getString("Name"),
                        resultSet.getString("Continent"),
                        resultSet.getString("Region")));
            }

            for (Country country : countries) {
                System.out.println(country.getName() + " " + country.getCode());
            }
            System.out.println(countries.size()); //toplam veri sayısı veriyor size  olarak

        } catch (SQLException exception) {
            helper.showErrorMessage(exception);
        } finally {
            connection.close();
        }

    }

    public static void insertData() throws SQLException {//INSERT İŞLEMLERİNDE PREPAREDSTATEMENT CLASSIYLA YAPILIR.
        //BU PARAMETRELERİ GENELDE KULLANICILARDAN ALIRIZ.
        //CONNECTİON AÇ
        //STATEMENT OLUŞTUR
        //VE ONU ÇALIŞTIR.
        Connection connection = null; //bağlantı kurmamız gerekiyor Bunu connection sınıfıyla yapıyoruz.
        DBHelper helper = new DBHelper(); //Dbhelper burada çağırıyoruz.
        //Helperı try üstüne tanımlıyorum ki catch kısmında da kullanabilmek için.
        PreparedStatement statement = null; //İnsert ifadeleri prepared statement denilen classlarla yapılır.
        //Verilen parametreleri genellikle kullanıcıdan alınır.
        ResultSet resultSet;
        try {
            connection = helper.getConnection(); //Connection açtık
            System.out.println("Bağlantı kuruldu...");
            statement = connection.prepareStatement("insert into city (Name,CountryCode,District,Population) values ('Düzce','TUR','Düzce',5000)");
            //Statementı oluştur...
            int result = statement.executeUpdate(); //ve ÇALIŞTIR.EXECUTEUPDATE DE BANA ETKİLENEN KAYIT SAYISI GELİR.
            //1 rows affected olur. Sonuc atıp görebilirsin
            System.out.println("Kayıt eklendi...");
        } catch (SQLException exception) { //Olur bir hatayla karşılaştırsan hata fırlatmasını istiyorum
            helper.showErrorMessage(exception);
        } finally {
            statement.close();
            connection.close(); //İşimiz bittiğinde bağlantıyı kapatsın istiyorum.
        }
    }

    public static void updateData() throws SQLException {

        Connection connection = null;
        DBHelper helper = new DBHelper();
        PreparedStatement statement = null; //İnsert ifadeleri prepared statement denilen classlarla yapılır.
        //Verilen parametreleri genellikle kullanıcıdan alınır.
        ResultSet resultSet;
        try {
            connection = helper.getConnection(); //Connection açtık
            System.out.println("Bağlantı kuruldu...");
            String sql = "update city set population=10000 where id =?";
            statement = connection.prepareStatement(sql);
            //Statementı oluştur...
            statement.setInt(1, 4080);  // ? işaretini set etti  1. soru işareti verdiğimiz değer int olduğu için 4080 verdik!!
            int result = statement.executeUpdate(); //ve ÇALIŞTIR.
            //1 rows affected olur. Sonuc atıp görebilirsin
            System.out.println("Kayıt güncellendi ...");
        } catch (SQLException exception) {
            helper.showErrorMessage(exception);
        } finally {
            statement.close(); //Statement mı ve connectionu kapatmamız lazım.
            connection.close();
        }
    }


    public static void deleteData() throws SQLException {
        Connection connection = null;
        DBHelper helper = new DBHelper();
        PreparedStatement statement = null; //İnsert ifadeleri prepared statement denilen classlarla yapılır.
        //Verilen parametreleri genellikle kullanıcıdan alınır.
        ResultSet resultSet;
        try {
            connection = helper.getConnection(); //Connection açtık
            System.out.println("Bağlantı kuruldu...");
            String sql = "delete from city where id = ?";
            statement = connection.prepareStatement(sql);
            //Statementı oluştur...
            statement.setInt(1, 4080);  // ? işaretini set etti  1. soru işareti verdiğimiz değer int olduğu için 4080 verdik!!
            int result = statement.executeUpdate(); //ve ÇALIŞTIR.
            //1 rows affected olur. Sonuc atıp görebilirsin
            System.out.println("Kayıt silindi...");
        } catch (SQLException exception) {
            helper.showErrorMessage(exception);
        } finally {
            statement.close(); //Statement mı ve connectionu kapatmamız lazım.
            connection.close();
        }


    }

}
