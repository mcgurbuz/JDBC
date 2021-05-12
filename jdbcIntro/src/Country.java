public class Country {
    //ORM nin tabanını oluşturur bu kısım. Veritabanım ile java classlarını map eder.
    private String Code;
    private String Name;
    private String Continent;
    private String Region;

    public Country(String Code,String Name,String Continent,String Region){
        this.Code = Code;
        this.Name = Name;
        this.Continent = Continent;
        this.Region = Region;
    }


    public String getCode() {
        return Code;
    }

    public String getName() {
        return Name;
    }

    public String getContinent() {
        return Continent;
    }

    public String getRegion() {
        return Region;
    }
}
