package com.example.guessnumberfragment.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.guessnumberfragment.R;
import com.example.guessnumberfragment.data.Jugador;
import com.example.guessnumberfragment.databinding.FragmentPlayBinding;

public class PlayFragment extends Fragment {

    private FragmentPlayBinding binding;
    public Jugador jugador;

    public PlayFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        // Inflate the layout for this fragment
        binding = FragmentPlayBinding.inflate(inflater);
        binding.btnAdivinar.setOnClickListener(view -> NavHostFragment.findNavController(this).navigate(R.id.action_playFragment_to_endPlayFragment));
        return binding.getRoot();


    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        //OPCION 1: Recoge un BUNDLE
        binding.setMessage(getArguments().getParcelable(Jugador.KEY));
        //OPCION 2: Se recoge a través de la clase FragmentBArgs
        //binding.setMessage(PlayFragment.fromBundle(getArguments()).getMessage());

        binding.tvBienvenida.setText("Bienvenido " + jugador.getNombre() + "!");
        binding.tvIntentosRestantes.setText("Te quedan " + String.valueOf(jugador.getnIntentos()) + " intentos");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    /**
     * Comprueba si el número introducido es mayor, menor o igual que el número a adivinar.
     */
    /*public void adivinar(View view) {
        if (!checkCampos())
            return;
        else {
            int numIntroducido = Integer.parseInt(binding.etNumero.getText().toString());
            Intent intent = new Intent(this, EndPlayFragment.class);
            AlertDialog.Builder dialogo = new AlertDialog.Builder(this)
                    .setCancelable(false)
                    .setTitle("Incorrecto")
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            binding.etNumero.setText("");
                            jugador.setnIntentosActual(jugador.getnIntentosActual() + 1);
                            jugador.setnIntentos(jugador.getnIntentos() - 1);
                            binding.tvIntentosRestantes.setText("Te quedan " + String.valueOf(jugador.getnIntentos()) + " intentos");
                        }
                    });

            if (numIntroducido == jugador.getNumAdivinar()) {
                victoria(intent);
                return;
            }
            if (numIntroducido < jugador.getNumAdivinar()) {
                menorNum(intent, dialogo);
                return;
            }
            if (numIntroducido > jugador.getNumAdivinar()) {
                mayorNum(intent, dialogo);
                return;
            }

        }
    }*/

    /**
     * Gestiona el programa en caso de que el jugador inserte un número mayor al número a adivinar
     */
    public void mayorNum(Intent intent, AlertDialog.Builder dialogo) {
        if (jugador.getnIntentos() == 1) {
            jugador.setPartida("perdido");
            jugador.setnIntentosActual(jugador.getnIntentosActual() + 1);
            startActivity(intent);
            //finish();
            return;
        }
        dialogo.setMessage("El número que ha introducido es MAYOR.");
        dialogo.show();
    }

    /**
     * Gestiona el programa en caso de que el jugador inserte un número menor al número a adivinar
     */
    public void menorNum(Intent intent, AlertDialog.Builder dialogo) {
        if (jugador.getnIntentos() == 1) {
            jugador.setPartida("perdido");
            jugador.setnIntentosActual(jugador.getnIntentosActual() + 1);
            startActivity(intent);
            //finish();
            return;
        }
        dialogo.setMessage("El número que ha introducido es MENOR.");
        dialogo.show();
    }

    /**
     * Gestiona el programa en caso de que el jugador inserte un número igual al número a adivinar
     */
    public void victoria(Intent intent) {
        jugador.setPartida("ganado");
        jugador.setnIntentosActual(jugador.getnIntentosActual() + 1);
        startActivity(intent);
        //finish();
    }

    /**
     * Verifica que ningún campo quede vacío
     */
    public boolean checkCampos() {
        String etNumero = binding.etNumero.getText().toString();

        if (etNumero.equals("")) {
            //Toast.makeText(this, "Todos los campos deben estar rellenos", Toast.LENGTH_SHORT).show();
            return false;
        } else
            return true;
    }
}