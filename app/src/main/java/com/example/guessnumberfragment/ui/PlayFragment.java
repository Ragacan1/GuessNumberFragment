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

        binding.setJugador(PlayFragmentArgs.fromBundle(getArguments()).getJugador());

        return binding.getRoot();


    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        binding.tvBienvenida.setText("Bienvenido " + binding.getJugador().getNombre() + "!");
        binding.tvIntentosRestantes.setText("Te quedan " + String.valueOf(binding.getJugador().getnIntentos()) + " intentos");
    }

    @Override
    public void onStart() {
        super.onStart();
        binding.btnAdivinar.setOnClickListener(view -> adivinar());

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    /**
     * Comprueba si el número introducido es mayor, menor o igual que el número a adivinar.
     */
    public void adivinar() {
        if (!checkCampos())
            return;
        else {
            int numIntroducido = Integer.parseInt(binding.etNumero.getText().toString());
            Intent intent = new Intent(getActivity(), EndPlayFragment.class);
            AlertDialog.Builder dialogo = new AlertDialog.Builder(getActivity())
                    .setCancelable(false)
                    .setTitle("Incorrecto")
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            binding.etNumero.setText("");
                            binding.getJugador().setnIntentosActual(binding.getJugador().getnIntentosActual() + 1);
                            binding.getJugador().setnIntentos(binding.getJugador().getnIntentos() - 1);
                            binding.tvIntentosRestantes.setText("Te quedan " + String.valueOf(binding.getJugador().getnIntentos()) + " intentos");
                        }
                    });

            if (numIntroducido == binding.getJugador().getNumAdivinar()) {
                victoria();
            }
            if (numIntroducido < binding.getJugador().getNumAdivinar()) {
                menorNum(dialogo);
                return;
            }
            if (numIntroducido > binding.getJugador().getNumAdivinar()) {
                mayorNum(dialogo);
                return;
            }
        }
    }

    /**
     * Gestiona el programa en caso de que el jugador inserte un número mayor al número a adivinar
     */
    public void mayorNum(AlertDialog.Builder dialogo) {
        if (binding.getJugador().getnIntentos() == 1) {
            binding.getJugador().setPartida("perdido");
            binding.getJugador().setnIntentosActual(binding.getJugador().getnIntentosActual() + 1);

            navegacion();
            return;
        }
        dialogo.setMessage("El número que ha introducido es MAYOR.");
        dialogo.show();
    }

    /**
     * Gestiona el programa en caso de que el jugador inserte un número menor al número a adivinar
     */
    public void menorNum(AlertDialog.Builder dialogo) {
        if (binding.getJugador().getnIntentos() == 1) {
            binding.getJugador().setPartida("perdido");
            binding.getJugador().setnIntentosActual(binding.getJugador().getnIntentosActual() + 1);

            navegacion();
            return;
        }
        dialogo.setMessage("El número que ha introducido es MENOR.");
        dialogo.show();
    }

    /**
     * Gestiona el programa en caso de que el jugador inserte un número igual al número a adivinar
     */
    public void victoria() {
        binding.getJugador().setPartida("ganado");
        binding.getJugador().setnIntentosActual(binding.getJugador().getnIntentosActual() + 1);
        navegacion();
    }

    /**
     * Verifica que ningún campo quede vacío
     */
    public boolean checkCampos() {
        String etNumero = binding.etNumero.getText().toString();

        if (etNumero.equals("")) {
            Toast.makeText(getActivity(), "Todos los campos deben estar rellenos", Toast.LENGTH_SHORT).show();
            return false;
        } else
            return true;
    }

    public void navegacion(){
        PlayFragmentDirections.ActionPlayFragmentToEndPlayFragment action = PlayFragmentDirections.actionPlayFragmentToEndPlayFragment(binding.getJugador());
        NavHostFragment.findNavController(this).navigate(action);
    }
}