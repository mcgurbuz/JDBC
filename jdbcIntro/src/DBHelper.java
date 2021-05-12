import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBHelper {
    //Bağlantı oluşturmamız gerekiyor.Bağlantı oluştururken veritabanında oluşacak kullanıcı bilgisine ihtiyacımız var
    private String userName = "root"; //Db mizin ismi
    private String password = "12345";//Db mizin şifresi
    //Biz nereye bağlanacağız? dbUrl ile buna bağlanıyoruz localhost sizin makinanız.
    //3306 default port MYSQL için ve bu server de world isimli veritabanına bağlanıyoruz.
    //Sizin veritabanınız neyse onu yazmanız gerekiyor. yazım kurallarına diikat ediniz.!!!
    private String dbUrl = "jdbc:mysql://localhost:3306/world";//Db mizin url port default world ise değişken tabloya göre değişi

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(dbUrl,userName,password); //Getconnection exception fırlattığı için yukarıdaki metodaSQLexception veriyoruz.
        //Driver manager connectorlere erişmemize ve yönetmeyi sağlayan class
        //Dburl username ve password burada veriyoruz.
    }
    public void showErrorMessage(SQLException exception){ //Veritabanıyla ilgili hataları bu metodum yönetsin.
        System.out.println("Hata mesajı : "+exception.getMessage());//Hata mesajını döndürür
        System.out.println("Hata kodu : "+exception.getErrorCode());//Veritabanındaki hatanın kodunu verir.
    }
}
