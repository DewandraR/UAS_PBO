/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package penjualan;

import java.awt.event.KeyEvent;
import java.awt.print.PrinterException;
import java.sql.*;
import java.text.*;
import java.util.Calendar;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ACER
 */
public class frmTransaksiPembelian extends javax.swing.JFrame {

    /**
     * Creates new form frmTransaksiPembelian
     */
Connection Con;
ResultSet RsBrg;
ResultSet RsSup;
Statement stm;
double total=0;
String tanggal;
Boolean edit=false;
PreparedStatement pstat;
String sKd_Sup;
String sKd_Brg;
String xnobeli;

DefaultTableModel tableModel = new DefaultTableModel(new Object [][]{}, new String [] {"Kd Barang", "Nama Barang","Harga Barang","Jumlah","Total"});
    public frmTransaksiPembelian() {
    initComponents();
    open_db();
    inisialisasi_tabel();
    aktif(false);
    setTombol(true);
    
cmdCetak.setEnabled(false);
txtTgl.setEditor(new JSpinner.DateEditor(txtTgl, "yyyy/MM/dd"));
    
    }
    
private void setField()
{
int row=tblBeli.getSelectedRow();
cmbKd_Brg.setSelectedItem((String)tblBeli.getValueAt(row,0));
txtNm_Brg.setText((String)tblBeli.getValueAt(row,1));
String hargaJual = Double.toString((Double)tblBeli.getValueAt(row,2));
txtHarga.setText(hargaJual);
String jumlah=Integer.toString((Integer)tblBeli.getValueAt(row,3));
txtJml.setText(jumlah);
String total=Double.toString((Double)tblBeli.getValueAt(row,4));
txtTot.setText(total);
}
    private void hitung_beli()
{
double xtot,xhrg;
int xjml;
xhrg=Double.parseDouble(txtHarga.getText());
xjml=Integer.parseInt(txtJml.getText());
xtot=xhrg*xjml;
String xtotal=Double.toString(xtot);
txtTot.setText(xtotal);
total=total+xtot;
txtTotal.setText(Double.toString(total));
}
private void open_db()
{
try{
KoneksiMysql kon = new KoneksiMysql ("localhost","root","","penjualan");
Con = kon.getConnection();
//System.out.println("Berhasil ");
}catch (Exception e) {
System.out.println("Error : "+e);
}
}
private void baca_supplier()
{
try{
stm=Con.createStatement();
pstat = Con.prepareStatement("select kd_sup,nm_sup from supplier",ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
ResultSet rs = pstat.executeQuery();
rs.beforeFirst();
while(rs.next())
{
cmbKd_Sup.addItem(rs.getString(1).trim());
}
rs.close();
}catch(SQLException e){
System.out.println("Error : "+e);
}
}
public void inisialisasi_tabel()
{
tblBeli.setModel(tableModel);
}

private void baca_barang()
{

try{
stm=Con.createStatement();
pstat = Con.prepareStatement("select * from barang",ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
ResultSet rs = pstat.executeQuery();
rs.beforeFirst();
while(rs.next())
{
cmbKd_Brg.addItem(rs.getString(1).trim());
}
rs.close();
}catch(SQLException e){
System.out.println("Error : "+e);
}
}
private void detail_barang(String xkode)
{
try{
stm=Con.createStatement();
pstat = Con.prepareStatement("select * from barang where kd_brg='"+xkode+"'",ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
ResultSet rs = pstat.executeQuery();
rs.beforeFirst();
while(rs.next())
{
txtNm_Brg.setText(rs.getString(2).trim());
txtHarga.setText(Double.toString((Double)rs.getDouble(4)));
}
rs.close();
}catch(SQLException e){
System.out.println("Error : "+e);
}
}
private void setTombol(boolean t)
{
cmdTambah.setEnabled(t);
cmdSimpan.setEnabled(!t);
cmdBatal.setEnabled(!t);
cmdKeluar.setEnabled(t);
cmdHapusItem.setEnabled(!t);
}
private void detail_supplier(String xkode)
{
try{
stm=Con.createStatement();

pstat = Con.prepareStatement("select * from supplier where kd_sup='"+xkode+"'",ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
ResultSet rs = pstat.executeQuery();
rs.beforeFirst();
while(rs.next()){
txtNama.setText(rs.getString(2).trim());
}
rs.close();
}catch(SQLException e){
System.out.println("Error : "+e);
}
}
private void kosong()
{
txtNoBeli.setText("");
txtNama.setText("");
txtHarga.setText("");
txtTotal.setText("");
text.setText("");
}
private void kosong_detail()
{
txtNm_Brg.setText("");
txtHarga.setText("");
txtJml.setText("");
txtTot.setText("");
}
private void aktif(boolean x)
{
cmbKd_Sup.setEnabled(x);
cmbKd_Brg.setEnabled(x);
cmbKd_Sup.removeAllItems();
cmbKd_Brg.removeAllItems();
txtTgl.setEnabled(x);
txtJml.setEditable(x);
}
private void nomor_beli()
{
try{
stm=Con.createStatement();
pstat = Con.prepareStatement("SELECT no_beli FROM jual ORDER BY no_beli DESC LIMIT1",ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
ResultSet rs = pstat.executeQuery();
int brs=0;
while(rs.next()){
brs=rs.getRow();
}

if(brs==0)
txtNoBeli.setText("1");
else{
rs.beforeFirst();
while(rs.next()) {
int no=rs.getInt("no_beli")+1;
txtNoBeli.setText(Integer.toString(no));
}
}
rs.close();
}catch(SQLException e){
System.out.println("Error : "+e);
}
}
private void format_tanggal()
{
String DATE_FORMAT = "yyyy-MM-dd";
java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(DATE_FORMAT);
Calendar c1 = Calendar.getInstance();
int year=c1.get(Calendar.YEAR);
int month=c1.get(Calendar.MONTH)+1;
int day=c1.get(Calendar.DAY_OF_MONTH);
tanggal=Integer.toString(year)+"-"+Integer.toString(month)+"-"+Integer.toString(day);
}
private void simpan_ditabel()
{
try{
String tKode=cmbKd_Brg.getSelectedItem().toString();
String tNama=txtNm_Brg.getText();
double hrgB=Double.parseDouble(txtHarga.getText());
int jml=Integer.parseInt(txtJml.getText());
double tot=Double.parseDouble(txtTot.getText());
tableModel.addRow(new Object[]{tKode,tNama,hrgB,jml,tot});
inisialisasi_tabel();
}catch(Exception e){
System.out.println("Error : "+e);
}
}
private void simpan_transaksi()
{
try{
xnobeli=txtNoBeli.getText();
format_tanggal();
String xkode=cmbKd_Sup.getSelectedItem().toString();
String msql="insert into beli values('"+xnobeli+"','"+xkode+"','"+tanggal+"')";
stm.executeUpdate(msql);
for(int i=0;i<tblBeli.getRowCount();i++)
{
String xkd=(String)tblBeli.getValueAt(i,0);
double xhrg=(Double)tblBeli.getValueAt(i,2);
int xjml=(Integer)tblBeli.getValueAt(i,3);

String zsql="insert into dbeli values('"+xnobeli+"','"+xkd+"',"+xhrg+","+xjml+")";
stm.executeUpdate(zsql);
//update stok
String ysql="update barang set stok=stok+"+xjml+" where kd_brg='"+xkd+"'";
stm.executeUpdate(ysql);
}
}catch(SQLException e){
System.out.println("Error : "+e);
}
}

private class PrintingTask extends SwingWorker<Object, Object> {
private final MessageFormat headerFormat;
private final MessageFormat footerFormat;
private final boolean interactive;
private volatile boolean complete = false;
private volatile String message;
public PrintingTask(MessageFormat header, MessageFormat footer, boolean interactive) {
this.headerFormat = header;
this.footerFormat = footer;
this.interactive = interactive;
}
@Override
protected Object doInBackground() {
try {
complete = text.print(headerFormat, footerFormat, true, null, null, interactive);
message = "Printing " + (complete ? "complete" : "canceled");
} catch (PrinterException ex) {
message = "Sorry, a printer error occurred";
} catch (SecurityException ex) {
message = "Sorry, cannot access the printer due to security reasons";
}
return null;
}
@Override
protected void done() {
//message(!complete, message);
}
}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtNoBeli = new javax.swing.JTextField();
        txtTgl = new javax.swing.JSpinner();
        cmbKd_Sup = new javax.swing.JComboBox<>();
        txtNama = new javax.swing.JTextField();
        cmbKd_Brg = new javax.swing.JComboBox<>();
        txtNm_Brg = new javax.swing.JTextField();
        txtHarga = new javax.swing.JTextField();
        txtJml = new javax.swing.JTextField();
        txtTot = new javax.swing.JTextField();
        cmdHapusItem = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblBeli = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        text = new javax.swing.JTextArea();
        cmdTambah = new javax.swing.JButton();
        cmdSimpan = new javax.swing.JButton();
        cmdBatal = new javax.swing.JButton();
        cmdCetak = new javax.swing.JButton();
        txtTotal = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        cmdKeluar = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("No Beli");

        jLabel2.setText("Tgl Beli");

        jLabel3.setText("Kode Supplier");

        jLabel4.setText("Nama Supplier");

        txtTgl.setModel(new javax.swing.SpinnerDateModel());

        cmbKd_Sup.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbKd_Sup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbKd_SupActionPerformed(evt);
            }
        });

        txtNama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNamaActionPerformed(evt);
            }
        });

        cmbKd_Brg.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbKd_Brg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbKd_BrgActionPerformed(evt);
            }
        });

        txtHarga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtHargaActionPerformed(evt);
            }
        });

        txtJml.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtJmlKeyPressed(evt);
            }
        });

        cmdHapusItem.setText("Hapus Item");
        cmdHapusItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdHapusItemActionPerformed(evt);
            }
        });

        tblBeli.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Kd Barang", "Nama Barang", "Harga", "Jumlah", "Total"
            }
        ));
        tblBeli.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblBeliMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblBeli);

        text.setColumns(20);
        text.setRows(5);
        jScrollPane2.setViewportView(text);

        cmdTambah.setText("Tambah");
        cmdTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdTambahActionPerformed(evt);
            }
        });

        cmdSimpan.setText("Simpan");
        cmdSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdSimpanActionPerformed(evt);
            }
        });

        cmdBatal.setText("Batal");
        cmdBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdBatalActionPerformed(evt);
            }
        });

        cmdCetak.setText("Cetak");
        cmdCetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdCetakActionPerformed(evt);
            }
        });

        jLabel5.setText("Total");

        cmdKeluar.setText("Keluar");
        cmdKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdKeluarActionPerformed(evt);
            }
        });

        jLabel6.setText("TRANSAKSI PEMBELIAN");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cmdTambah)
                        .addGap(34, 34, 34)
                        .addComponent(cmdSimpan)
                        .addGap(29, 29, 29)
                        .addComponent(cmdBatal)
                        .addGap(33, 33, 33)
                        .addComponent(cmdCetak)
                        .addGap(44, 44, 44)
                        .addComponent(cmdKeluar))
                    .addComponent(cmdHapusItem)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cmbKd_Brg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtNm_Brg, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtHarga, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtJml, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(txtTot, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel2)
                                    .addGap(18, 18, 18)
                                    .addComponent(txtTgl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel1)
                                    .addGap(18, 18, 18)
                                    .addComponent(txtNoBeli, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel4)
                                .addComponent(jLabel3))
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cmbKd_Sup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 419, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5)
                            .addGap(48, 48, 48)
                            .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 606, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(cmbKd_Sup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel1)
                                    .addComponent(txtNoBeli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2)
                                    .addComponent(txtTgl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbKd_Brg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNm_Brg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtHarga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtJml, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTot, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(cmdHapusItem)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtTotal)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmdTambah)
                    .addComponent(cmdSimpan)
                    .addComponent(cmdBatal)
                    .addComponent(cmdCetak)
                    .addComponent(cmdKeluar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmbKd_SupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbKd_SupActionPerformed
 JComboBox cKd_Sup = (javax.swing.JComboBox)evt.getSource();
sKd_Sup = (String)cKd_Sup.getSelectedItem();
detail_supplier(sKd_Sup);
    }//GEN-LAST:event_cmbKd_SupActionPerformed

    private void cmbKd_BrgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbKd_BrgActionPerformed
JComboBox cKd_Brg = (javax.swing.JComboBox)evt.getSource();
sKd_Brg = (String)cKd_Brg.getSelectedItem();
detail_barang(sKd_Brg);
txtJml.setText("");
txtTot.setText("");
    }//GEN-LAST:event_cmbKd_BrgActionPerformed

    private void txtJmlKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtJmlKeyPressed
if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
hitung_beli();
simpan_ditabel();
}
    }//GEN-LAST:event_txtJmlKeyPressed

    private void cmdHapusItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdHapusItemActionPerformed
        DefaultTableModel dataModel = (DefaultTableModel) tblBeli.getModel();
int row=tblBeli.getSelectedRow();
if (tblBeli.getRowCount() > 0) {
dataModel.removeRow(row);
}
double xtot,xhrg;
int xjml;
xhrg=Double.parseDouble(txtHarga.getText());
xjml=Integer.parseInt(txtJml.getText());
xtot=xhrg*xjml;
total=total-xtot;
txtTotal.setText(Double.toString(total));
    }//GEN-LAST:event_cmdHapusItemActionPerformed

    private void tblBeliMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBeliMouseClicked
        setField();
    }//GEN-LAST:event_tblBeliMouseClicked

    private void cmdTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdTambahActionPerformed
aktif(true);
setTombol(false);
kosong();
baca_supplier();
baca_barang();
nomor_beli();
    }//GEN-LAST:event_cmdTambahActionPerformed

    private void cmdSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdSimpanActionPerformed
simpan_transaksi();
aktif(false);
setTombol(true);
kosong();
kosong_detail();
cmdCetak.setEnabled(true);
    }//GEN-LAST:event_cmdSimpanActionPerformed

    private void cmdBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdBatalActionPerformed
aktif(false);
setTombol(true);
kosong();
    }//GEN-LAST:event_cmdBatalActionPerformed

    private void cmdCetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdCetakActionPerformed
 format_tanggal();
String ctk="Nota Pembelian\nNo : "+xnobeli+"\nTanggal : "+tanggal;
ctk=ctk+"\n"+"-------------------------------------------------------------------------------------------------";
ctk=ctk+"\n"+"Kode\tNama Barang\tHarga\tJml\tTotal";
ctk=ctk+"\n"+"-------------------------------------------------------------------------------------------------";
for(int i=0;i<tblBeli.getRowCount();i++)
{
String xkd=(String)tblBeli.getValueAt(i,0);
String xnama=(String)tblBeli.getValueAt(i,1);
double xhrg=(Double)tblBeli.getValueAt(i,2);
int xjml=(Integer)tblBeli.getValueAt(i,3);
double xtot=(Double)tblBeli.getValueAt(i,4);
ctk=ctk+"\n"+xkd+"\t"+xnama+"\t"+xhrg+"\t"+xjml+"\t"+xtot;
}
ctk=ctk+"\n"+"-------------------------------------------------------------------------------------------------";
text.setText(ctk);
String headerField="";
String footerField="";
MessageFormat header = new MessageFormat(headerField);
MessageFormat footer = new MessageFormat(footerField);;
boolean interactive = true;//interactiveCheck.isSelected();
boolean background = true;//backgroundCheck.isSelected();
PrintingTask task = new PrintingTask(header, footer, interactive);
if (background) {
task.execute();
} else {
task.run();
}
    }//GEN-LAST:event_cmdCetakActionPerformed

    private void cmdKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdKeluarActionPerformed
        dispose();
    }//GEN-LAST:event_cmdKeluarActionPerformed

    private void txtNamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNamaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNamaActionPerformed

    private void txtHargaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHargaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHargaActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frmTransaksiPembelian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmTransaksiPembelian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmTransaksiPembelian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmTransaksiPembelian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmTransaksiPembelian().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cmbKd_Brg;
    private javax.swing.JComboBox<String> cmbKd_Sup;
    private javax.swing.JButton cmdBatal;
    private javax.swing.JButton cmdCetak;
    private javax.swing.JButton cmdHapusItem;
    private javax.swing.JButton cmdKeluar;
    private javax.swing.JButton cmdSimpan;
    private javax.swing.JButton cmdTambah;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblBeli;
    private javax.swing.JTextArea text;
    private javax.swing.JTextField txtHarga;
    private javax.swing.JTextField txtJml;
    private javax.swing.JTextField txtNama;
    private javax.swing.JTextField txtNm_Brg;
    private javax.swing.JTextField txtNoBeli;
    private javax.swing.JSpinner txtTgl;
    private javax.swing.JTextField txtTot;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}
