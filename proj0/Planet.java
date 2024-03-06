public class Planet
{
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    public Planet(double xP, double yP, double xV, double yV, double m, String img)
    {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet p)
    {
        this.xxPos = p.xxPos;
        this.yyPos = p.yyPos;
        this.xxVel = p.xxVel;
        this.yyVel = p.yyVel;
        this.mass = p.mass;
        this.imgFileName = p.imgFileName;
    }

    public double calcDistance (Planet p)
    {
        double dx = this.xxPos - p.xxPos;
        double dy = this.yyPos - p.yyPos;
        double r = Math.sqrt((dx * dx) + (dy * dy));
        return r;
    }

    public static double G = 6.67e-11;
    public double calcForceExertedBy(Planet p)
    {
        double r = this.calcDistance(p);
        double F = (G * this.mass * p.mass) / (r * r);
        return F;
    }

    public double calcForceExertedByX(Planet p)
    {
        double r = this.calcDistance(p);
        double dx = p.xxPos - this.xxPos;
        double F = this.calcForceExertedBy(p);
        double F_x = (F * dx) / r;
        return F_x;
    }

    public double calcForceExertedByY(Planet p)
    {
        double r = this.calcDistance(p);
        double dy = p.yyPos - this.yyPos;
        double F = this.calcForceExertedBy(p);
        double F_y = (F * dy) / r;
        return F_y;
    }

    public boolean equals(Planet p)
    {
        if (this != p)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public double calcNetForceExertedByX(Planet[] allPlanets)
    {
        double Fnet_x = 0;
        for (Planet p : allPlanets)
        {
            if (this.equals(p))
            {
                Fnet_x += this.calcForceExertedByX(p);
            }
        }
        return Fnet_x;
    }

    public double calcNetForceExertedByY(Planet[] allPlanets)
    {
        double Fnet_y = 0;
        for (Planet P1 : allPlanets)
        {
            if (this.equals(P1))
            {
                Fnet_y += this.calcForceExertedByY(P1);
            }
        }
        return Fnet_y;
    }

    public void update(double dt, double fX, double fY)
    {
        double aX = fX / this.mass;
        double aY = fY / this.mass;
        this.xxVel = this.xxVel + dt * aX;
        this.yyVel = this.yyVel + dt * aY;
        this.xxPos = this.xxPos + dt * xxVel;
        this.yyPos = this.yyPos + dt * yyVel;
    }

    public void draw()
    {
        String image = "images/" + this.imgFileName;
        StdDraw.picture(this.xxPos, this.yyPos, image);
    }
}

