import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

  
public class tablaPeriodica2 extends JFrame implements ActionListener {
    
    
 
    private JButton H,Li,Na,K,Rb,Cs,Fr,Co,Rh,Ir,Mt,O,S,Se,Te,Po,Uuh,salir,Be,Mg,Ca,Sr,Ba,Ra,Ni,Pd,Pt,Ds,F,Cl,Br,
           I,At,Uus,Tm,Yb,Lu,Sc,Y,Lan,Act,Cu,Ag,Au,Rg,La,Ce,Pr,Nd,Pm,Sm,Eu,Gd,Tb,Dy,Ho,Er,Ti,Zr,Hf,Rf,Zn,Cd,Hg,
           Cn,Ac,Th,Pa,U,Np,Pu,Am,Cm,Bk,Cf,Es,Fm,V,Nb,Ta,Db,B,Al,Ga,In,TI,Uut,Md,No,Lr,Fe,Ru,Os,Hs,N,P,As,Sb,
           Bi,Uup,Mn,Tc,Re,Bh,C,Si,Ge,Sn,Pb,Uuq,He,Ne,Ar,Kr,Xe,Rn,Uuo,Cr,Mo,W,Sg;
    private JLabel msj;

  
    public tablaPeriodica2(){
     setLayout(null);
     
     msj = new JLabel (" TABLA PERIODICA DE LOS ELEMENTOS QUIMICOS ");
      msj.setBounds(250, 10,300, 300);
      
      add(msj);
     
      H = new JButton ("H");
      H.setBounds(10, 100,50, 50);
      H.setBackground(Color.blue);
      add(H);
      H.addActionListener(this);
     
      Li = new JButton ("Li");
      Li.setBounds(10, 150, 50, 50);
      Li.setBackground(Color.YELLOW);
      add(Li);
      Li.addActionListener(this);
      Na = new JButton ("Na");
      Na.setBounds(10, 200, 50, 50);
      Na.setBackground(Color.YELLOW);
      add(Na);
         Na.addActionListener(this);
      K = new JButton ("K");
      K.setBounds(10, 250, 50, 50);
      K.setBackground(Color.YELLOW);
      add(K);
         K.addActionListener(this);
      Rb = new JButton ("Rb");
      Rb.setBounds(10, 300, 50, 50);
      Rb.setBackground(Color.YELLOW);
      add(Rb);
        Rb.addActionListener(this);
      Cs = new JButton ("Cs");
      Cs.setBounds(10, 350, 50, 50);
      Cs.setBackground(Color.YELLOW);
      add(Cs);
        Cs.addActionListener(this);
      Fr = new JButton ("Fr");
      Fr.setBounds(10, 400, 50, 50);
      Fr.setBackground(Color.YELLOW);
      add(Fr);
         Fr.addActionListener(this);
      //iniciamos Grupo 2
        Be = new JButton ("Be");
        Be.setBounds(60, 150, 50, 50);
        Be.setBackground(Color.PINK);
        add(Be);
        Be.addActionListener(this);
        Mg = new JButton ("Mg");
        Mg.setBounds(60, 200, 50, 50);
        Mg.setBackground(Color.PINK);
        add(Mg);
        Mg.addActionListener(this);
        Ca = new JButton ("Ca");
        Ca.setBounds(60, 250, 50, 50);
        Ca.setBackground(Color.PINK);
        add(Ca);
        Ca.addActionListener(this);
        Sr = new JButton ("Sr");
        Sr.setBounds(60, 300, 50, 50);
        Sr.setBackground(Color.PINK);
        add(Sr);
        Sr.addActionListener(this);
        Ba = new JButton ("Ba");
        Ba.setBounds(60, 350, 50, 50);
        Ba.setBackground(Color.PINK);
        add(Ba);
        Ba.addActionListener(this);
        Ra = new JButton ("Ra");
        Ra.setBounds(60, 400, 50, 50);
        Ra.setBackground(Color.PINK);
        add(Ra);
        Ra.addActionListener(this);
        // Iniciamos Maquetacion del grupo 3
            Sc = new JButton ("Sc");
            Sc.setBounds(110, 250, 50, 50);
            Sc.setBackground(Color.MAGENTA);
            add(Sc);
            Sc.addActionListener(this);
            Y = new JButton ("Y");
            Y.setBounds(110, 300, 50, 50);
            Y.setBackground(Color.MAGENTA);
            add(Y);
            Y.addActionListener(this);
            La = new JButton ("LA");
            La.setBounds(110,500, 50, 50);
            La.setBackground(Color.MAGENTA);
            add(La);
            La.addActionListener(this);
            Act = new JButton ("AC");
            Act.setBounds(110, 400, 50, 50);
            Act.setBackground(Color.MAGENTA);
            add(Act);
            Act.addActionListener(this);
            // iniciamos el Grupo 4
               Ti = new JButton ("Ti");
               Ti.setBounds(160, 250, 50, 50);
               Ti.setBackground(Color.MAGENTA);
               add(Ti);
               Ti.addActionListener(this);
               Zr = new JButton ("Zr");
               Zr.setBounds(160, 300, 50, 50);
               Zr.setBackground(Color.MAGENTA);
               add(Zr);
               Zr.addActionListener(this);
               Hf = new JButton ("Hf");
               Hf.setBounds(160, 350, 50, 50);
               Hf.setBackground(Color.MAGENTA);
               add(Hf);
                 Hf.addActionListener(this);
               Rf = new JButton ("Rf");
               Rf.setBounds(160,400, 50, 50);
               Rf.setBackground(Color.CYAN);
               add(Rf);
                   Rf.addActionListener(this);
           /// iniciando Grupo 5
                V = new JButton ("V");
                V.setBounds(210, 250, 50, 50);
                V.setBackground(Color.MAGENTA);
                add(V);
                  V.addActionListener(this);
                Nb = new JButton ("Nb");
                Nb.setBounds(210, 300, 50, 50);
                Nb.setBackground(Color.MAGENTA);
                add(Nb);
                Nb.addActionListener(this);
                Ta = new JButton ("Ta");
                Ta.setBounds(210, 350, 50, 50);
                Ta.setBackground(Color.MAGENTA);
                add(Ta);
                Ta.addActionListener(this);
                Db = new JButton ("Db");
                Db.setBounds(210, 400, 50, 50);
                Db.setBackground(Color.CYAN);
                add(Db);
                Db.addActionListener(this);
                //// grupo 6 corregido
                Cr = new JButton ("Cr");
                Cr.setBounds(260, 250, 50, 50);
                Cr.setBackground(Color.MAGENTA);
                add(Cr);
                  Cr.addActionListener(this);
                Mo = new JButton ("Mo");
                Mo.setBounds(260, 300, 50, 50);
                Mo.setBackground(Color.MAGENTA);
                add(Mo);
                Mo.addActionListener(this);
                W = new JButton ("W");
                W.setBounds(260, 350, 50, 50);
                W.setBackground(Color.MAGENTA);
                add(W);
                W.addActionListener(this);
                Sg = new JButton ("Sg");
                Sg.setBounds(260, 400, 50, 50);
                Sg.setBackground(Color.CYAN);
                add(Sg);
                Sg.addActionListener(this);
                   
                
              ///// Grupo 7
                Mn = new JButton("mn");
                Mn.setBounds(310, 250, 50, 50);
                Mn.setBackground(Color.MAGENTA);
                add(Mn);
                Mn.addActionListener(this);
                Tc = new JButton ("Tc");
                Tc.setBounds(310, 300, 50, 50);
                Tc.setBackground(Color.MAGENTA);
                add(Tc);
                Tc.addActionListener(this);
                Re = new JButton ("Re");
                Re.setBounds(310, 350, 50, 50);
                Re.setBackground(Color.MAGENTA);
                add(Re);
                Re.addActionListener(this);
                Bh = new JButton ("Bh");
                Bh.setBounds(310, 400, 50, 50);
                Bh.setBackground(Color.CYAN);
                add(Bh);
                Bh.addActionListener(this);
                  // grupo 8
                      Fe = new JButton ("Fe");
                      Fe.setBounds(360, 250, 50, 50);
                      Fe.setBackground(Color.RED);
                      add(Fe);
                      Fe.addActionListener(this);
                      Ru = new JButton ("Ru");
                      Ru.setBounds(360,300, 50, 50);
                      Ru.setBackground(Color.RED);
                      add(Ru);
                       Ru.addActionListener(this);
                      Os = new JButton ("Os");
                      Os.setBounds(360, 350, 50, 50);
                      Os.setBackground(Color.RED);
                      add(Os);
                      Os.addActionListener(this);
                      Hs = new JButton ("Hs");
                      Hs.setBounds(360,400, 50, 50);
                      Hs.setBackground(Color.CYAN);
                      add(Hs);
                      Hs.addActionListener(this);
     //grupo 9
     Co = new JButton ("Co");
     Co.setBounds(410, 250, 50, 50);
     Co.setBackground(Color.RED);
     add(Co);Co.addActionListener(this);
     Rh = new JButton ("Rh");
     Rh.setBounds(410,300, 50, 50);
     Rh.setBackground(Color.RED);
     add(Rh);Rh.addActionListener(this);
     Ir = new JButton ("Ir");
     Ir.setBounds(410,350, 50, 50);
     Ir.setBackground(Color.RED);
     add(Ir);Ir.addActionListener(this);
     Mt = new JButton ("Mt");
     Mt.setBounds(410,400, 50, 50);
     Mt.setBackground(Color.CYAN);
     add(Mt);Mt.addActionListener(this);
      // Grupo 10
         Ni = new JButton ("Ni");
         Ni.setBounds(460, 250, 50, 50);
         Ni.setBackground(Color.RED);
         add(Ni);Ni.addActionListener(this);
         Pd = new JButton ("Pd");
         Pd.setBounds(460,300, 50, 50);
         Pd.setBackground(Color.RED);
         add(Pd);Pd.addActionListener(this);
         Pt = new JButton ("pt");
         Pt.setBounds(460, 350, 50, 50);
         Pt.setBackground(Color.RED);
         add(Pt);Pt.addActionListener(this);
         Ds = new JButton ("Ds");
         Ds.setBounds(460,400, 50, 50);
         Ds.setBackground(Color.lightGray);
         add(Ds);Ds.addActionListener(this);
           /// grupo 11
               Cu = new JButton ("Cu");
               Cu.setBounds(510, 250, 50, 50);
               Cu.setBackground(Color.lightGray);
               add(Cu);Cu.addActionListener(this);
               Ag = new JButton ("Ag");
               Ag.setBounds(510,300, 50, 50);
               Ag.setBackground(Color.lightGray);
               add(Ag);Ag.addActionListener(this);
               Au = new JButton ("Au");
               Au.setBounds(510, 350, 50, 50);
               Au.setBackground(Color.lightGray);
               add(Au);Au.addActionListener(this);
               Rg = new JButton ("Rg");
               Rg.setBounds(510, 400, 50, 50);
               Rg.setBackground(Color.lightGray);
               add(Rg);Rg.addActionListener(this);
               ///   grupo 12 
                     Zn = new JButton ("Zn");
                     Zn.setBounds(560, 250, 50, 50);
                     Zn.setBackground(Color.lightGray);
                     add(Zn);Zn.addActionListener(this);
                     Cd = new JButton ("Cd");
                     Cd.setBounds(560,300, 50, 50);
                     Cd.setBackground(Color.lightGray);
                     add(Cd);Cd.addActionListener(this);
                     Hg = new JButton ("Hg");
                     Hg.setBounds(560, 350, 50, 50);
                     Hg.setBackground(Color.lightGray);
                     add(Hg);Hg.addActionListener(this);
                     Cn = new JButton ("Cn");
                     Cn.setBounds(560,400, 50, 50);
                     Cn.setBackground(Color.lightGray);
                     add(Cn);Cn.addActionListener(this);
                      //// grupo 13
                           B = new JButton ("B");
                           B.setBounds(610, 150, 50, 50);
                           B.setBackground(Color.CYAN);
                           add(B);B.addActionListener(this);
                           Al = new JButton ("Al");
                           Al.setBounds(610, 200, 50, 50);
                           Al.setBackground(Color.gray);
                           add(Al);Al.addActionListener(this);
                           Ga = new JButton ("Ga");
                           Ga.setBounds(610, 250, 50, 50);
                           Ga.setBackground(Color.gray);
                           add(Ga);Ga.addActionListener(this);
                           In = new JButton ("In");
                           In.setBounds(610, 300, 50, 50);
                           In.setBackground(Color.gray);
                           add(In);In.addActionListener(this);
                           TI = new JButton ("TI");
                           TI.setBounds(610, 350, 50, 50);
                           TI.setBackground(Color.gray);
                           add(TI);TI.addActionListener(this);
                           Uut = new JButton ("ut");
                           Uut.setBounds(610, 400, 50, 50);
                           Uut.setBackground(Color.gray);
                           add(Uut);Uut.addActionListener(this);
                           //grupo 14
                                 C = new JButton ("C");
                                 C.setBounds(660, 150, 50, 50);
                                 C.setBackground(Color.green);
                                 add(C);C.addActionListener(this);
                                 Si = new JButton ("Si");
                                 Si.setBounds(660, 200, 50, 50);
                                 Si.setBackground(Color.CYAN);
                                 add(Si);Si.addActionListener(this);
                                 Ge = new JButton ("Ge");
                                 Ge.setBounds(660, 250, 50, 50);
                                 Ge.setBackground(Color.CYAN);
                                 add(Ge);Ge.addActionListener(this);
                                 Sn = new JButton ("Sn");
                                 Sn.setBounds(660, 300, 50, 50);
                                 Sn.setBackground(Color.gray);
                                 add(Sn);Sn.addActionListener(this);
                                 Pb = new JButton ("Pb");
                                 Pb.setBounds(660, 350, 50, 50);
                                 Pb.setBackground(Color.gray);
                                 add(Pb);Pb.addActionListener(this);
                                 Uuq = new JButton ("Uq");
                                 Uuq.setBounds(660, 400, 50, 50);
                                 Uuq.setBackground(Color.gray);
                                 add(Uuq);Uuq.addActionListener(this);
  // grupo 15
     N = new JButton ("N");
     N.setBounds(710, 150, 50, 50);
     N.setBackground(Color.green);
     add(N);N.addActionListener(this);
     P = new JButton ("P");
     P.setBounds(710, 200, 50, 50);
     P.setBackground(Color.green);
     add(P);P.addActionListener(this);
     As = new JButton ("As");
     As.setBounds(710, 250, 50, 50);
     As.setBackground(Color.CYAN);
     add(As);As.addActionListener(this);
     Sb = new JButton ("Sb");
     Sb.setBounds(710, 300, 50, 50);
     Sb.setBackground(Color.CYAN);
     add(Sb);Sb.addActionListener(this);
     Bi = new JButton ("Bi");
     Bi.setBounds(710, 350, 50, 50);
     Bi.setBackground(Color.gray);
     add(Bi);Bi.addActionListener(this);
     Uup = new JButton ("Up");
     Uup.setBounds(710, 400, 50, 50);
     Uup.setBackground(Color.gray);
     add(Uup);Uup.addActionListener(this);
      //// grupo 16
                                 O = new JButton ("O");
                                 O.setBounds(760, 150, 50, 50);
                                 O.setBackground(Color.green);
                                 add(O);O.addActionListener(this);
                                 O.addActionListener(this);
                                 S = new JButton ("S");
                                 S.setBounds(760, 200, 50, 50);
                                 S.setBackground(Color.green);
                                 add(S);S.addActionListener(this);
                                 Se = new JButton ("Se");
                                 Se.setBounds(760, 250, 50, 50);
                                 Se.setBackground(Color.green);
                                 add(Se);Se.addActionListener(this);
                                 Te = new JButton ("Te");
                                 Te.setBounds(760, 300, 50, 50);
                                 Te.setBackground(Color.CYAN);
                                 add(Te);Te.addActionListener(this);
                                 Po = new JButton ("Po");
                                 Po.setBounds(760, 350, 50, 50);
                                 Po.setBackground(Color.CYAN);
                                 add(Po);Po.addActionListener(this);
                                 Uuh = new JButton ("Uh");
                                 Uuh.setBounds(760, 400, 50, 50);
                                 Uuh.setBackground(Color.gray);
                                 add(Uuh);Uuh.addActionListener(this);
      /// grupo 17
     F = new JButton ("F");
     F.setBounds(810, 150, 50, 50);
     F.setBackground(Color.yellow);
     add(F);F.addActionListener(this);
     Cl = new JButton ("Cl");
     Cl.setBounds(810, 200, 50, 50);
     Cl.setBackground(Color.yellow);
     add(Cl);Cl.addActionListener(this);
     Br = new JButton ("Br");
     Br.setBounds(810, 250, 50, 50);
     Br.setBackground(Color.yellow);
     add(Br);Br.addActionListener(this);
     I = new JButton ("I");
     I.setBounds(810, 300, 50, 50);
     I.setBackground(Color.yellow);
     add(I);I.addActionListener(this);
     At = new JButton ("At");
     At.setBounds(810, 350, 50, 50);
     At.setBackground(Color.yellow);
     add(At);At.addActionListener(this);
     Uus = new JButton ("Us");
     Uus.setBounds(810, 400, 50, 50);
     Uus.setBackground(Color.yellow);
     add(Uus);Uus.addActionListener(this);
              /// grupo 18
              He = new JButton ("He");
              He.setBounds(860, 100, 50, 50);
              He.setBackground(Color.WHITE);
              add(He);He.addActionListener(this);
              Ne = new JButton ("Ne");
              Ne.setBounds(860, 150, 50, 50);
              Ne.setBackground(Color.white);
              add(Ne);Ne.addActionListener(this);
              Ar = new JButton ("Ar");
              Ar.setBounds(860, 200, 50, 50);
              Ar.setBackground(Color.white);
              add(Ar);Ar.addActionListener(this);
              Kr = new JButton ("Kr");
              Kr.setBounds(860, 250, 50, 50);
              Kr.setBackground(Color.white);
              add(Kr);Kr.addActionListener(this);
              Xe = new JButton ("Xe");
              Xe.setBounds(860, 300, 50, 50);
              Xe.setBackground(Color.white);
              add(Xe);Xe.addActionListener(this);
              Rn = new JButton ("Rn");
              Rn.setBounds(860, 350, 50, 50);
              Rn.setBackground(Color.white);
              add(Rn);Rn.addActionListener(this);
              Uuo = new JButton ("Uuo");
              Uuo.setBounds(860, 400, 50, 50);
              Uuo.setBackground(Color.white);
              add(Uuo);Uuo.addActionListener(this);
                         /// Lantanidos
                           add(La);La.addActionListener(this);
                           Ce = new JButton ("Ce");
                           Ce.setBounds(160,500, 50, 50);
                           Ce.setBackground(Color.pink);
                           add(Ce);Ce.addActionListener(this);
                           Pr = new JButton ("Pr");
                           Pr.setBounds(210,500, 50, 50);
                           Pr.setBackground(Color.pink);
                           add(Pr);Pr.addActionListener(this);
                           Nd = new JButton ("Nd");
                           Nd.setBounds(260,500, 50, 50);
                           Nd.setBackground(Color.pink);
                           add(Nd);Nd.addActionListener(this);
                           Pm = new JButton ("Pm");
                           Pm.setBounds(310,500, 50, 50);
                           Pm.setBackground(Color.pink);
                           add(Pm);Pm.addActionListener(this);
                           Sm = new JButton ("sm");
                           Sm.setBounds(360,500, 50, 50);
                           Sm.setBackground(Color.pink);
                           add(Sm);Sm.addActionListener(this);
                           Eu = new JButton ("Eu");
                           Eu.setBounds(410,500, 50, 50);
                           Eu.setBackground(Color.pink);
                           add(Eu);Eu.addActionListener(this);
                           Gd = new JButton ("Gd");
                           Gd.setBounds(460,500, 50, 50);
                           Gd.setBackground(Color.pink);
                           add(Gd);Gd.addActionListener(this);
                           Tb = new JButton ("Tb");
                           Tb.setBounds(510,500, 50, 50);
                           Tb.setBackground(Color.pink);
                           add(Tb);Tb.addActionListener(this);
                           Dy = new JButton ("Dy");
                           Dy.setBounds(560,500, 50, 50);
                           Dy.setBackground(Color.pink);
                           add(Dy);Dy.addActionListener(this);
                           Ho = new JButton ("Ho");
                           Ho.setBounds(610,500, 50, 50);
                           Ho.setBackground(Color.pink);
                           add(Ho);Ho.addActionListener(this);
                           Er = new JButton ("Er");
                           Er.setBounds(660,500, 50, 50);
                           Er.setBackground(Color.pink);
                           add(Er);Er.addActionListener(this);
                           Tm = new JButton ("TM");
                           Tm.setBounds(710,500, 50, 50);
                           Tm.setBackground(Color.pink);
                           add(Tm);Tm.addActionListener(this);
                           Yb = new JButton ("Yb");
                           Yb.setBounds(760,500, 50, 50);
                           Yb.setBackground(Color.pink);
                           add(Yb);Yb.addActionListener(this);
                           Lu = new JButton ("Lu");
                           Lu.setBounds(810,500, 50, 50);
                           Lu.setBackground(Color.pink);
                           add(Lu);Lu.addActionListener(this);
                           
  ///Actinos
     Ac = new JButton ("Ac");
     Ac.setBounds(110,550, 50, 50);
     Ac.setBackground(Color.magenta);
     add(Ac);Ac.addActionListener(this);
     Th = new JButton ("Th");
     Th.setBounds(160,550, 50, 50);
     Th.setBackground(Color.magenta);
     add(Th);Th.addActionListener(this);
     Pa = new JButton ("Pa");
     Pa.setBounds(210,550, 50, 50);
     Pa.setBackground(Color.magenta);
     add(Pa);
     Pa.addActionListener(this);
     U = new JButton ("U");
     U.setBounds(260,550, 50, 50);
     U.setBackground(Color.magenta);
     add(U);U.addActionListener(this);
     Np = new JButton ("Np");
     Np.setBounds(310,550, 50, 50);
     Np.setBackground(Color.magenta);
     add(Np);Np.addActionListener(this);
     Pu = new JButton ("Pu");
     Pu.setBounds(360,550, 50, 50);
     Pu.setBackground(Color.magenta);
     add(Pu);Pu.addActionListener(this);
     Am = new JButton ("Am");
     Am.setBounds(410,550, 50, 50);
     Am.setBackground(Color.magenta);
     add(Am);Am.addActionListener(this);
     Cm = new JButton ("Cm");
     Cm.setBounds(460,550, 50, 50);
     Cm.setBackground(Color.magenta);
     add(Cm);Cm.addActionListener(this);
     Bk = new JButton ("Bk");
     Bk.setBounds(510,550, 50, 50);
     Bk.setBackground(Color.magenta);
     add(Bk);Bk.addActionListener(this);
     Cf = new JButton ("Cf");
     Cf.setBounds(560,550, 50, 50);
     Cf.setBackground(Color.magenta);
     add(Cf);Cf.addActionListener(this);
         
     Es = new JButton ("Es");
     Es.setBounds(610,550, 50, 50);
     Es.setBackground(Color.magenta);
     add(Es);
           Es.addActionListener(this);
     Fm = new JButton ("fm");
     Fm.setBounds(660,550, 50, 50);
     Fm.setBackground(Color.magenta);
     add(Fm);
          Fm.addActionListener(this);
     Md = new JButton ("Md");
     Md.setBounds(710,550, 50, 50);
     Md.setBackground(Color.magenta);
     add(Md);
           Md.addActionListener(this);
     No = new JButton ("No");
     No.setBounds(760,550, 50, 50);
     No.setBackground(Color.magenta);
     add(No);
        No.addActionListener(this);
     Lr = new JButton ("Lr");
     Lr.setBounds(810,550, 50, 50);
     Lr.setBackground(Color.magenta);
     add(Lr);
     Lr.addActionListener(this);
     // termina la maquetacion del proyecto tabla periodica :)
        salir = new JButton("Salir");
        salir.setBounds(600, 630, 100, 30);
        add(salir);
        salir.addActionListener(this);
        /////////////////////////////
  
    }
   
   
    
    public void actionPerformed(ActionEvent evento){
       Container f = this.getContentPane();
       if(evento.getSource()== H){
            JOptionPane.showMessageDialog(null, " Hidrogeno \n numero atomico : 1"
                    + "\n Peso Atomico: 1.0079 \n Simbolo: H" ); 
       }
       if(evento.getSource()== Li){
            JOptionPane.showMessageDialog(null, " Litio \n numero atomico :3"
                    + "\n Peso Atomico: 6.941 \n Simbolo: Li" ); 
       }
       if(evento.getSource()== Na){
            JOptionPane.showMessageDialog(null, " Sodio \n numero atomico : 11"
                    + "\n Peso Atomico: 22.9898 \n Simbolo: Na" ); 
       }
       if(evento.getSource()== K){
            JOptionPane.showMessageDialog(null, " Potasio \n numero atomico : 19"
                    + "\n Peso Atomico: 39.0983 \n Simbolo: k" ); 
       }
       if(evento.getSource()== Rb){
            JOptionPane.showMessageDialog(null, " Rubidio \n numero atomico : 37"
                    + "\n Peso Atomico: 85.4678 \n Simbolo: Rb" );  
       }
       if(evento.getSource()== Cs){
            JOptionPane.showMessageDialog(null, " Cesio \n numero atomico : 55"
                    + "\n Peso Atomico:132.9054\n Simbolo: Cs" ); 
       }
       if(evento.getSource()== Fr){
            JOptionPane.showMessageDialog(null, " Francio \n numero atomico : 87"
                    + "\n Peso Atomico: 223.02 \n Simbolo: Fr" ); 
       }
       if(evento.getSource()== Be){
            JOptionPane.showMessageDialog(null, " Berilio \n numero atomico : 4"
                    + "\n Peso Atomico: 9.0122 \n Simbolo: Be" ); 
       }
       if(evento.getSource()== Mg){
            JOptionPane.showMessageDialog(null, " Magnesio \n numero atomico : 12"
                    + "\n Peso Atomico: 24.3050 \n Simbolo: Mg" ); 
       }
       if(evento.getSource()== Ca){
            JOptionPane.showMessageDialog(null, " Calcio \n numero atomico : 20"
                    + "\n Peso Atomico: 40.078 \n Simbolo: Ca" ); 
       }
       if(evento.getSource()== Sr){
            JOptionPane.showMessageDialog(null, " Estroncio \n numero atomico :38"
                    + "\n Peso Atomico: 87.62 \n Simbolo: Sr" );   
       }
       if(evento.getSource()== Ba){
            JOptionPane.showMessageDialog(null, " Bario \n numero atomico : 56"
                    + "\n Peso Atomico: 137.327 \n Simbolo: Ba" ); 
       }
       if(evento.getSource()== Ra){
            JOptionPane.showMessageDialog(null, " Radio \n numero atomico : 88"
                    + "\n Peso Atomico: 226.0254 \n Simbolo: Ra" ); 
       }
       if(evento.getSource()== Sc){
            JOptionPane.showMessageDialog(null, " Escandio \n numero atomico : 21"
                    + "\n Peso Atomico: 44.9559 \n Simbolo: Sc" ); 
       }
       if(evento.getSource()== Y){
           JOptionPane.showMessageDialog(null, " Itrio \n numero atomico : 39"
                    + "\n Peso Atomico: 88.9059 \n Simbolo: Y" ); 
       }
       if(evento.getSource()== Act){
            JOptionPane.showMessageDialog(null, "Serie Del Actino !!" );
       }
        if(evento.getSource()== La ){
            JOptionPane.showMessageDialog(null, " Lantano \n numero atomico : 57"
                    + "\n Peso Atomico: 138.9055 \n Simbolo: La" );   
       }
       if(evento.getSource()== Ti){
            JOptionPane.showMessageDialog(null, " Titanio \n numero atomico :22"
                    + "\n Peso Atomico: 47.867 \n Simbolo: Ti" );  
       }
       if(evento.getSource()== Zr){
            JOptionPane.showMessageDialog(null, " Circonio \n numero atomico : 40"
                    + "\n Peso Atomico: 91.224 \n Simbolo: Zr" ); 
       }
       if(evento.getSource()== Hf){
            JOptionPane.showMessageDialog(null, " Hafnio \n numero atomico : 72"
                    + "\n Peso Atomico: 178.49 \n Simbolo: Hf" ); 
       }
       if(evento.getSource()== Rf){
            JOptionPane.showMessageDialog(null, "Rutherfordio \n numero atomico : 104"
                    + "\n Peso Atomico: 621.11 \n Simbolo: Rf" ); 
       }
       if(evento.getSource()== V ){
            JOptionPane.showMessageDialog(null, " Vanadio \n numero atomico : 23"
                    + "\n Peso Atomico: 50.9415 \n Simbolo: V " ); 
       }
       if(evento.getSource()== Nb){
            JOptionPane.showMessageDialog(null, " Niobio \n numero atomico : 41"
                    + "\n Peso Atomico: 92.9064 \n Simbolo: Nb" ); 
       }
       if(evento.getSource()== Ta){
            JOptionPane.showMessageDialog(null, " Tantalio \n numero atomico : 73"
                    + "\n Peso Atomico: 180.9474 \n Simbolo: Ta" ); 
       }
       if(evento.getSource()== Db){
            JOptionPane.showMessageDialog(null, " Dubnio \n numero atomico : 105"
                    + "\n Peso Atomico: 262.11 \n Simbolo: Db" );
       }
       if(evento.getSource()== Cr ){
            JOptionPane.showMessageDialog(null, " Cromo \n numero atomico : 24"
                    + "\n Peso Atomico: 51.9961 \n Simbolo: Cr" ); 
       }
       if(evento.getSource()== Mo ){
            JOptionPane.showMessageDialog(null, " Molibdeno \n numero atomico :42"
                    + "\n Peso Atomico: 95.94 \n Simbolo: Mo" ); 
       }
       if(evento.getSource()== W ){
            JOptionPane.showMessageDialog(null, " Tugsteno \n numero atomico : 74"
                    + "\n Peso Atomico: 183.84 \n Simbolo: W" ); 
       }
       if(evento.getSource()== Sg ){
            JOptionPane.showMessageDialog(null, " Seaborgio \n numero atomico : 106"
                    + "\n Peso Atomico: 263.12 \n Simbolo: Sg" ); 
       }
       if(evento.getSource()== Mn ){
            JOptionPane.showMessageDialog(null, " Manganeso \n numero atomico : 25"
                    + "\n Peso Atomico: 54.9380 \n Simbolo: Mn" ); 
       }
       if(evento.getSource()== Tc ){
            JOptionPane.showMessageDialog(null, " Tecnesio \n numero atomico : 43"
                    + "\n Peso Atomico: 97.907 \n Simbolo: Tc" ); 
       }
       if(evento.getSource()== Re ){
            JOptionPane.showMessageDialog(null, " Renio \n numero atomico : 75"
                    + "\n Peso Atomico: 186.207 \n Simbolo: Re" ); 
       }
       if(evento.getSource()== Bh ){
            JOptionPane.showMessageDialog(null, " Bohrio \n numero atomico : 107"
                    + "\n Peso Atomico: 262.12 \n Simbolo: Bh" ); 
       }
       if(evento.getSource()== Fe ){
            JOptionPane.showMessageDialog(null, " Hierro \n numero atomico : 26"
                    + "\n Peso Atomico: 55.845 \n Simbolo: Fe" ); 
       }
       if(evento.getSource()== Ru ){
            JOptionPane.showMessageDialog(null, " Rutenio \n numero atomico : 44"
                    + "\n Peso Atomico: 101.07 \n Simbolo: Ru" ); 
       }
       if(evento.getSource()== Os ){
            JOptionPane.showMessageDialog(null, " Osmio \n numero atomico : 76"
                    + "\n Peso Atomico: 190.2 \n Simbolo: Os" ); 
       }
       if(evento.getSource()== Hs ){
            JOptionPane.showMessageDialog(null, " Hassnio \n numero atomico : 108"
                    + "\n Peso Atomico: 265 \n Simbolo: Hs" ); 
       }
       if(evento.getSource()== Co ){
            JOptionPane.showMessageDialog(null, " Cobalto \n numero atomico : 27"
                    + "\n Peso Atomico: 58.9332 \n Simbolo: Co" ); 
       }
       if(evento.getSource()== Rh ){
            JOptionPane.showMessageDialog(null, " Rodio \n numero atomico : 45"
                    + "\n Peso Atomico: 102.9055 \n Simbolo: Rh" ); 
       }
       if(evento.getSource()== Ir ){
            JOptionPane.showMessageDialog(null, " Iridio \n numero atomico : 77"
                    + "\n Peso Atomico: 192.22 \n Simbolo: Ir" ); 
       }
       if(evento.getSource()== Mt ){
            JOptionPane.showMessageDialog(null, " Meitnerio \n numero atomico : 109"
                    + "\n Peso Atomico: 266 \n Simbolo: Mt" ); 
       }
       if(evento.getSource()== Ni ){
            JOptionPane.showMessageDialog(null, " Niquel \n numero atomico : 28"
                    + "\n Peso Atomico: 58.6394 \n Simbolo: Ni" ); 
       }
       if(evento.getSource()== Pd ){
            JOptionPane.showMessageDialog(null, " Paladio \n numero atomico : 46"
                    + "\n Peso Atomico: 106.42 \n Simbolo: Pd" ); 
       }
       if(evento.getSource()== Pt ){
            JOptionPane.showMessageDialog(null, " Platino \n numero atomico : 78"
                    + "\n Peso Atomico: 195.08 \n Simbolo: Pt" ); 
       }
       if(evento.getSource()== Ds ){
            JOptionPane.showMessageDialog(null, "  Descubierto en 1994" ); 
       }
       if(evento.getSource()== Cu ){
            JOptionPane.showMessageDialog(null, " Cobre \n numero atomico : 29"
                    + "\n Peso Atomico: 63.546 \n Simbolo: Cu" ); 
       }
       if(evento.getSource()== Ag ){
            JOptionPane.showMessageDialog(null, " Plata \n numero atomico : 47"
                    + "\n Peso Atomico: 107.8682 \n Simbolo: Ag" ); 
       }
       if(evento.getSource()== Au ){
            JOptionPane.showMessageDialog(null, " Oro \n numero atomico : 79"
                    + "\n Peso Atomico: 196.9665 \n Simbolo: Au" ); 
       }
       if(evento.getSource()== Rg ){
            JOptionPane.showMessageDialog(null, " Descubierto en 1994" ); 
       }
       if(evento.getSource()== Zn ){
            JOptionPane.showMessageDialog(null, " Cinc \n numero atomico : 30"
                    + "\n Peso Atomico: 65.39 \n Simbolo: Zn" ); 
       }
       if(evento.getSource()== Cd ){
            JOptionPane.showMessageDialog(null, " Cabmio \n numero atomico : 48"
                    + "\n Peso Atomico: 112.411 \n Simbolo: Cd" ); 
       }
       if(evento.getSource()== Hg ){
            JOptionPane.showMessageDialog(null, " Mercurio \n numero atomico : 80"
                    + "\n Peso Atomico: 200.59 \n Simbolo: Hg" ); 
       }
       if(evento.getSource()== Cn ){
            JOptionPane.showMessageDialog(null, "  Descubierto en 1996 \n Sin mas Datos" ); 
       }
       if(evento.getSource()== B ){
            JOptionPane.showMessageDialog(null, "Boro \n numero atomico : 5"
                    + "\n Peso Atomico: 10.811 \n Simbolo: B" ); 
       }
       if(evento.getSource()== Al ){
            JOptionPane.showMessageDialog(null, " Alumino \n numero atomico : 13"
                    + "\n Peso Atomico: 26.9815 \n Simbolo: Al" ); 
       }
       if(evento.getSource()== Ga ){
            JOptionPane.showMessageDialog(null, " Galio \n numero atomico : 31"
                    + "\n Peso Atomico: 69.723 \n Simbolo: Ga" );  
       }
       if(evento.getSource()== In ){
            JOptionPane.showMessageDialog(null, " Indio \n numero atomico : 49"
                    + "\n Peso Atomico: 114.818 \n Simbolo: In" ); 
       }
       if(evento.getSource()== TI ){
            JOptionPane.showMessageDialog(null, " Talio \n numero atomico : 81"
                    + "\n Peso Atomico: 204.3833 \n Simbolo: TI" );   
       }
       if(evento.getSource()== Uut ){
            JOptionPane.showMessageDialog(null, "Sin Datos :( " ); 
       }
       if(evento.getSource()== C ){
            JOptionPane.showMessageDialog(null, " Carbono \n numero atomico : 6"
                    + "\n Peso Atomico: 12.011 \n Simbolo: C" ); 
       }
       if(evento.getSource()== Si ){
            JOptionPane.showMessageDialog(null,"Silicio \n numero atomico : 14"
                    + "\n Peso Atomico: 28.0855 \n Simbolo: Si" ); 
       }
       if(evento.getSource()== Ge ){
            JOptionPane.showMessageDialog(null, " Germanio \n numero atomico : 32"
                    + "\n Peso Atomico:72.61 \n Simbolo: Ge" ); 
       }
       if(evento.getSource()== Sn ){
            JOptionPane.showMessageDialog(null, " Estaño \n numero atomico : 50"
                    + "\n Peso Atomico: 118.710 \n Simbolo: Sn" ); 
       }
       if(evento.getSource()== Pb ){
            JOptionPane.showMessageDialog(null, " Plomo \n numero atomico : 82"
                    + "\n Peso Atomico: 207.2 \n Simbolo: Pb" ); 
       }
       if(evento.getSource()== Uuq ){
            JOptionPane.showMessageDialog(null, " Descubierto en 1996, sin mas Datos "); 
       }
       if(evento.getSource()== N ){
            JOptionPane.showMessageDialog(null, " Nitrogeno \n numero atomico : 7"
                    + "\n Peso Atomico: 14.0067 \n Simbolo: N" ); 
       }
       if(evento.getSource()== P ){
            JOptionPane.showMessageDialog(null, " Fosforo \n numero atomico : 15"
                    + "\n Peso Atomico: 30.9738 \n Simbolo: P" ); 
       }
       if(evento.getSource()== As ){
            JOptionPane.showMessageDialog(null, " Arsenico \n numero atomico : 33"
                    + "\n Peso Atomico: 74.9216 \n Simbolo: As" ); 
       }
       if(evento.getSource()== Sb ){
            JOptionPane.showMessageDialog(null, " Antimonio \n numero atomico : 51"
                    + "\n Peso Atomico: 121.760 \n Simbolo: Sb" ); 
       }
       if(evento.getSource()== Bi ){
            JOptionPane.showMessageDialog(null, "Bismuto \n numero atomico : 83"
                    + "\n Peso Atomico: 208.9804 \n Simbolo: Bi" ); 
       }
       if(evento.getSource()== Uup ){
            JOptionPane.showMessageDialog(null, "     \n numero atomico :   "
                    + "\n Peso Atomico:    \n Simbolo: Uup" ); 
       }
       if(evento.getSource()== O ){
            JOptionPane.showMessageDialog(null, " Oxigeno \n numero atomico : 8"
                    + "\n Peso Atomico: 15.9994 \n Simbolo: O" );   
       }
       if(evento.getSource()== S ){
            JOptionPane.showMessageDialog(null, " AZufre \n numero atomico : 16"
                    + "\n Peso Atomico: 32.066 \n Simbolo: S" ); 
       }
       if(evento.getSource()== Se ){
            JOptionPane.showMessageDialog(null, "Selenio \n numero atomico : 34"
                    + "\n Peso Atomico: 78.96 \n Simbolo: Se" ); 
       }
       if(evento.getSource()== Te ){
            JOptionPane.showMessageDialog(null, " Telurio \n numero atomico : 52"
                    + "\n Peso Atomico: 127.60 \n Simbolo: Te" ); 
       } 
       if(evento.getSource()== Po ){
            JOptionPane.showMessageDialog(null, " Polonio \n numero atomico : 84"
                    + "\n Peso Atomico: 208.98 \n Simbolo: Po" ); 
       }
       if(evento.getSource()== Uuh ){
            JOptionPane.showMessageDialog(null, "    \n numero atomico :   "
                    + "\n Peso Atomico:  \n Simbolo: " ); 
       }
       if(evento.getSource()== F ){
            JOptionPane.showMessageDialog(null, " Fluor \n numero atomico : 9"
                    + "\n Peso Atomico: 18.9984 \n Simbolo: F" ); 
       }
       if(evento.getSource()== Cl ){
            JOptionPane.showMessageDialog(null, " Cloro \n numero atomico : 17"
                    + "\n Peso Atomico: 35.427 \n Simbolo: Cl" ); 
       }
       if(evento.getSource()== Br ){
            JOptionPane.showMessageDialog(null, " Bromo \n numero atomico : 35"
                    + "\n Peso Atomico: 79.904 \n Simbolo: Br" ); 
       }
       if(evento.getSource()== I ){
            JOptionPane.showMessageDialog(null, " Yodo \n numero atomico : 53"
                    + "\n Peso Atomico: 126.9045 \n Simbolo: I" ); 
       }
       if(evento.getSource()== At ){
            JOptionPane.showMessageDialog(null, " Astato \n numero atomico : 85"
                    + "\n Peso Atomico: 209.99 \n Simbolo: At" ); 
       }
       if(evento.getSource()== Uus ){
            JOptionPane.showMessageDialog(null, "     \n numero atomico :    "
                    + "\n Peso Atomico:      \n Simbolo:   " ); 
       }
       if(evento.getSource()== He ){
            JOptionPane.showMessageDialog(null, " Helio \n numero atomico : 2"
                    + "\n Peso Atomico: 4.0026 \n Simbolo: He" ); 
       }
       if(evento.getSource()== Ne ){
            JOptionPane.showMessageDialog(null, " Neon \n numero atomico : 10"
                    + "\n Peso Atomico: 20.1797 \n Simbolo: Ne" );   
       }
       if(evento.getSource()== Ar ){
            JOptionPane.showMessageDialog(null, " Argon \n numero atomico : 18"
                    + "\n Peso Atomico: 39.948 \n Simbolo: Ar" ); 
       }
       if(evento.getSource()== Kr ){
            JOptionPane.showMessageDialog(null, " Cripton \n numero atomico : 36"
                    + "\n Peso Atomico: 83.80 \n Simbolo: Kr" ); 
       }
       if(evento.getSource()== Xe ){
            JOptionPane.showMessageDialog(null, " Xenon \n numero atomico : 54"
                    + "\n Peso Atomico: 131.29 \n Simbolo: Xe" ); 
       }
       if(evento.getSource()== Rn ){
           JOptionPane.showMessageDialog(null, " Radon \n numero atomico : 86"
                    + "\n Peso Atomico: 222.02 \n Simbolo: Rn" ); 
       }
       if(evento.getSource()== Uuo ){
            JOptionPane.showMessageDialog(null, "     \n numero atomico :   "
                    + "\n Peso Atomico:    \n Simbolo:   " ); 
       }
       //Lantanidos
       if(evento.getSource()== Ce ){
            JOptionPane.showMessageDialog(null, " Cerio \n numero atomico : 58"
                    + "\n Peso Atomico: 140.115 \n Simbolo: Ce" );   
       }
       if(evento.getSource()== Pr ){
          JOptionPane.showMessageDialog(null, " Praseodimo \n numero atomico : 59"
                    + "\n Peso Atomico: 140.9076 \n Simbolo: Pr" ); 
       }
       if(evento.getSource()== Nd ){
           JOptionPane.showMessageDialog(null, " Neodimio \n numero atomico : 60"
                    + "\n Peso Atomico: 144.24 \n Simbolo: Nd" ); 
       }
       if(evento.getSource()== Pm ){
            JOptionPane.showMessageDialog(null, " Prometio \n numero atomico : 61"
                    + "\n Peso Atomico: 24.3050 \n Simbolo: Pm" ); 
       }
       if(evento.getSource()== Sm ){
            JOptionPane.showMessageDialog(null, " Samario \n numero atomico : 62 "
                    + "\n Peso Atomico: 150.36 \n Simbolo: Sm" ); 
       }
       if(evento.getSource()== Eu ){
            JOptionPane.showMessageDialog(null, " Europio \n numero atomico : 63"
                    + "\n Peso Atomico: 151.965 \n Simbolo: Eu" ); 
       }
       if(evento.getSource()== Gd ){
           JOptionPane.showMessageDialog(null, " Gadolinio \n numero atomico : 64"
                    + "\n Peso Atomico: 157025 \n Simbolo: Gd" );   
       }
       if(evento.getSource()== Tb ){
            JOptionPane.showMessageDialog(null, " Terbio \n numero atomico : 65"
                    + "\n Peso Atomico: 158.929 \n Simbolo: Tb " ); 
       }
       if(evento.getSource()== Dy ){
            JOptionPane.showMessageDialog(null, " Disprosio \n numero atomico : 66"
                    + "\n Peso Atomico: 162.50 \n Simbolo: Dy" ); 
       }
       if(evento.getSource()== Ho ){
            JOptionPane.showMessageDialog(null, " Holmio \n numero atomico : 67"
                    + "\n Peso Atomico: 164.9303 \n Simbolo: Ho" ); 
       }
       if(evento.getSource()== Er ){
           JOptionPane.showMessageDialog(null, " Erbio  \n numero atomico : 68"
                    + "\n Peso Atomico: 167.26 \n Simbolo: Er" ); 
       }
       if(evento.getSource()== Tm ){
            JOptionPane.showMessageDialog(null, " Tulio \n numero atomico : 69"
                    + "\n Peso Atomico: 168.9342 \n Simbolo: Tm" ); 
       }
       if(evento.getSource()== Yb ){
           JOptionPane.showMessageDialog(null, " Iterbio \n numero atomico : 70"
                    + "\n Peso Atomico: 173.04 \n Simbolo: Yb" ); 
       }
       if(evento.getSource()== Lu ){
            JOptionPane.showMessageDialog(null, " Lutecio \n numero atomico : 71"
                    + "\n Peso Atomico: 174.967 \n Simbolo: Lu" );  
       }
       //// ACtinos 
       if(evento.getSource()== Ac ){
            JOptionPane.showMessageDialog(null, " Actino \n numero atomico : 89"
                    + "\n Peso Atomico: 227. \n Simbolo: Ac" ); 
       }
       if(evento.getSource()== Th ){
           JOptionPane.showMessageDialog(null, " Torio \n numero atomico : 90"
                    + "\n Peso Atomico: 232.0381 \n Simbolo: Th" ); 
       }
       if(evento.getSource()== Pa ){
             JOptionPane.showMessageDialog(null, " Protactino \n numero atomico : 91"
                    + "\n Peso Atomico: 231.0388 \n Simbolo: Pa" );  
       }
       if(evento.getSource()== U ){
             JOptionPane.showMessageDialog(null, " Uranio \n numero atomico : 92"
                    + "\n Peso Atomico: 238.0289 \n Simbolo: U" ); 
       }
       if(evento.getSource()== Np ){
             JOptionPane.showMessageDialog(null, " Neptunio \n numero atomico : 93"
                    + "\n Peso Atomico: 237.0482 \n Simbolo: Np" ); 
       }
       if(evento.getSource()== Pu ){
             JOptionPane.showMessageDialog(null, " Protactino \n numero atomico : 94"
                    + "\n Peso Atomico: 244.664 \n Simbolo: Pu" ); 
       }
       if(evento.getSource()== Am ){
             JOptionPane.showMessageDialog(null, " Amaricio \n numero atomico : 95"
                    + "\n Peso Atomico: 243.061 \n Simbolo: Am" ); 
       }
       if(evento.getSource()== Cm ){
             JOptionPane.showMessageDialog(null, " Curio \n numero atomico : 96"
                    + "\n Peso Atomico: 247.07 \n Simbolo: Cm" ); 
       }
       if(evento.getSource()== Bk ){
             JOptionPane.showMessageDialog(null, " Berkelio \n numero atomico : 97"
                    + "\n Peso Atomico: 247.07 \n Simbolo: Bk" ); 
       }
       if(evento.getSource()== Cf ){
             JOptionPane.showMessageDialog(null, " Californio \n numero atomico : 98"
                    + "\n Peso Atomico: 251.08 \n Simbolo: Cf" ); 
       }
       if(evento.getSource()== Es ){
             JOptionPane.showMessageDialog(null, " Einsteno \n numero atomico : 99"
                    + "\n Peso Atomico: 252.08 \n Simbolo: Es" ); 
       }
       if(evento.getSource()== Fm ){
            JOptionPane.showMessageDialog(null, " Fermio \n numero atomico : 100"
                    + "\n Peso Atomico: 257.100 \n Simbolo: Fm" ); 
       }
       if(evento.getSource()== Md ){
            JOptionPane.showMessageDialog(null, " Mendelevio \n numero atomico : 101"
                    + "\n Peso Atomico: 258.10 \n Simbolo: Md" ); 
       }
       if(evento.getSource()== No ){
             JOptionPane.showMessageDialog(null, " Nobelio \n numero atomico : 102"
                    + "\n Peso Atomico: 259.10 \n Simbolo: No" ); 
       }
       if(evento.getSource()== Lr ){
             JOptionPane.showMessageDialog(null, " Laurencio \n numero atomico : 103"
                    + "\n Peso Atomico: 262.11 \n Simbolo: Lr" ); 
       }
       
       if(evento.getSource()== salir){
           System.exit(0);
       }
   }
   
    public static void main(String[] args) {
      tablaPeriodica2 ventana = new tablaPeriodica2();
     
      ventana.setBounds(05,05,950,700);
      ventana.setVisible(true);
      ventana.setResizable(false);
         
              }
}