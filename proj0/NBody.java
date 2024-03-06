public class NBody {
    public static double readRadius(String a)
    {
        In in = new In(a);
        int num = in.readInt();
        double radius = in.readDouble();
        return radius;
    }

    public static Planet[] readPlanets (String a)
    {
        In in = new In(a);
        int num = in.readInt();
        double radius = in.readDouble();
        Planet[] planet = new Planet[num];
        for (int i = 0; i < num; i++)
        {
            if(!in.isEmpty())
            {
                double xxP = in.readDouble();
                double yyPos = in.readDouble();
                double xxVel = in.readDouble();
                double yyVel = in.readDouble();
                double mass = in.readDouble();
                String imgFileName = in.readString();
                planet[i] = new Planet(xxP, yyPos, xxVel, yyVel, mass, imgFileName);
            }
        }
        return planet;
    }
    public static String background = "images/starfield.jpg";
    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double Radi = readRadius(filename);
        Planet[] plan = readPlanets(filename);

        StdDraw.enableDoubleBuffering();


        double time = 0;
        while (time <= T)
        {
            int leng = plan.length;
            double[] xForces = new double[plan.length];
            double[] yForces = new  double[plan.length];
            for (int i = 0; i < leng; i++)
            {
                xForces[i] = plan[i].calcNetForceExertedByX(plan);
                yForces[i] = plan[i].calcNetForceExertedByY(plan);
            }

            for (int i = 0; i < leng; i++)
            {
                plan[i].update(dt, xForces[i], yForces[i]);
            }

            StdDraw.setScale(-Radi, Radi);
            StdDraw.picture(0, 0, background);
            for (int j = 0; j < leng; j++)
            {
                plan[j].draw();
            }
            StdDraw.show();
            StdDraw.pause(10);

            time += dt;
        }
        StdOut.printf("%d\n", plan.length);
        StdOut.printf("%.2e\n", Radi);
        for (int i = 0; i < plan.length; i++)
        {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    plan[i].xxPos, plan[i].yyPos, plan[i].xxVel,
                    plan[i].yyVel, plan[i].mass, plan[i].imgFileName);
        }
    }
}
