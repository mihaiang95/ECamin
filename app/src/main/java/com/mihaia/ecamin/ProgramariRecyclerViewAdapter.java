package com.mihaia.ecamin;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mihaia.ecamin.DataContracts.Programare;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by Mihai on 6/21/2017.
 */

public class ProgramariRecyclerViewAdapter extends RecyclerView.Adapter<ProgramariRecyclerViewAdapter.ViewHolder>{

    private Context context;
    private List<Programare> mDataset;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView IdProgramare, data, IdUser, IdMasina;
        public TextView IsDel;
        public ImageButton btnDetalii, btnDelete;
        public TableLayout layoutDetalii;
        //TextView ziPlata, lunaPlata, ziScadenta, lunaScadenta;

        public ViewHolder(View view) {
            super(view);

            layoutDetalii = (TableLayout) view.findViewById(R.id.tableLayoutProgramari_Detalii);

            btnDetalii = (ImageButton) view.findViewById(R.id.iBtnProgramari_Detalii);
            btnDelete = (ImageButton) view.findViewById(R.id.iBtnProgramari_Delete);

            IdProgramare = (TextView) view.findViewById(R.id.tvProgramari_IdProgramare);
            data = (TextView) view.findViewById(R.id.tvProgramari_Data);
            IdUser = (TextView) view.findViewById(R.id.tvProgramari_Etaj_TempUser);
            IdMasina = (TextView) view.findViewById(R.id.tvProgramari_IdMasina);
            IsDel = (TextView) view.findViewById(R.id.isDel);

            layoutDetalii.setVisibility(View.GONE);

            btnDetalii.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(layoutDetalii.getVisibility() == View.VISIBLE){
                        layoutDetalii.setVisibility(View.GONE);
                        btnDetalii.setBackgroundResource(R.drawable.more_24);
                    } else {
                        layoutDetalii.setVisibility(View.VISIBLE);
                        btnDetalii.setBackgroundResource(R.drawable.less_24);
                    }
                }
            });


        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public ProgramariRecyclerViewAdapter(Context context,List<Programare> dataset) {
        this.context = context;
        mDataset = dataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ProgramariRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                             int viewType) {
        // create a new view
        View v = (View) LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.element_lista_programari, parent, false);


        ProgramariRecyclerViewAdapter.ViewHolder vh = new ProgramariRecyclerViewAdapter.ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ProgramariRecyclerViewAdapter.ViewHolder holder, int poz) {

        holder.IdProgramare.setText(String.valueOf(mDataset.get(poz).Id_Programare));
        holder.IdMasina.setText(String.valueOf(mDataset.get(poz).Id_Masina));
        holder.IdUser.setText(String.valueOf(mDataset.get(poz).Id_User));

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy kk:mm");
        holder.data.setText(dateFormat.format(mDataset.get(poz).Data_Ora));


        if(mDataset.get(poz).IsDel == true)
            holder.IsDel.setText("1");
        else
            holder.IsDel.setText("0");

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);

                builder.setMessage(R.string.dialog_ProgramareSterge_Mesaj)
                        .setTitle(R.string.dialog_ProgramareSterge_Titlu);

                builder.setPositiveButton(R.string.da, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(context, "A ales Da", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });

                builder.setNegativeButton(R.string.nu, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(context, "A ales Nu", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}


