import { useEffect, useState } from 'react'
import TarjetaDia from './TarjetaDia';

const nombresDias = ["Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo"];

function App() {
  const API_BASE = import.meta.env.VITE_API_BASE_URL || "http://localhost:8082";
  const [semana, setSemana] = useState([]);
  
  // Estados para el formulario de alta
  const [nuevaTarea, setNuevaTarea] = useState({ dia: "", hora: "", texto: "" });

  // 1. Función para traer las tareas
  const obtenerTareas = () => {
    fetch(`${API_BASE}/tareas`)
      .then(respuesta => respuesta.json())
      .then(datos => setSemana(datos))
      .catch(error => console.error("Error al conectar: ", error));
  };

  useEffect(() => {
    obtenerTareas();
  }, []);

  // 2. Función para AGREGAR (POST)
  const agregarTarea = (e) => {
    e.preventDefault(); // Evita que el formulario recargue la página

    fetch(`${API_BASE}/tareas`, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(nuevaTarea)
    })
    .then(() => {
      obtenerTareas(); // Recargamos las tareas
      setNuevaTarea({ dia: "", hora: "", texto: "" }); // Limpiamos el formulario
    })
     .catch(error => {
      console.error("Error al guardar la tarea: ", error);
      alert("Error al guardar. Fijate si el servidor de Spring Boot está corriendo.");
    });
  };

  // 3. Función para ELIMINAR (DELETE)
  const eliminarTarea = (dia, numTarea) => {
    fetch(`${API_BASE}/tareas/${dia}/${numTarea}`, {
      method: "DELETE"
    })
    .then(() => obtenerTareas());
  };

  const modificarTarea = (dia, numTarea, nuevosValores) => {
    if (!nuevosValores) return;
    fetch(`${API_BASE}/tareas/${dia}/${numTarea}`, {
      method: "PUT",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(nuevosValores)
    })
    .then(() => obtenerTareas())
    .catch(error => console.error("Error en PUT: ", error));
  };

    return (
    <div className="p-8 bg-gray-50 min-h-screen font-sans">
      <h1 className="text-3xl font-bold mb-8 text-gray-800">
        Mi Agenda Web
      </h1>

      {/* --- FORMULARIO PARA AGREGAR --- */}
      <div className="bg-white p-6 rounded-lg shadow-md mb-8 border border-gray-200">
        <h2 className="text-xl font-bold text-gray-700 mb-4">Agregar Nueva Tarea</h2>
        <form onSubmit={agregarTarea} className="flex gap-4 items-end">
          <div className="flex-1">
            <label className="block text-sm font-medium text-gray-600 mb-1">Día</label>
            <input 
              type="text" 
              placeholder="Ej: Lunes" 
              className="w-full p-2 border border-gray-300 rounded focus:ring-2 focus:ring-blue-500 outline-none"
              value={nuevaTarea.dia}
              onChange={(e) => setNuevaTarea({...nuevaTarea, dia: e.target.value})}
              required
            />
          </div>
          <div className="flex-1">
            <label className="block text-sm font-medium text-gray-600 mb-1">Hora</label>
            <input 
              type="text" 
              placeholder="Ej: 10:00" 
              className="w-full p-2 border border-gray-300 rounded focus:ring-2 focus:ring-blue-500 outline-none"
              value={nuevaTarea.hora}
              onChange={(e) => setNuevaTarea({...nuevaTarea, hora: e.target.value})}
              required
            />
          </div>
          <div className="flex-2">
            <label className="block text-sm font-medium text-gray-600 mb-1">Tarea</label>
            <input 
              type="text" 
              placeholder="¿Qué tenés que hacer?" 
              className="w-full p-2 border border-gray-300 rounded focus:ring-2 focus:ring-blue-500 outline-none"
              value={nuevaTarea.texto}
              onChange={(e) => setNuevaTarea({...nuevaTarea, texto: e.target.value})}
              required
            />
          </div>
          <button 
            type="submit" 
            className="bg-blue-600 text-white px-6 py-2 rounded font-bold hover:bg-blue-700 transition-colors"
          >
            Guardar
          </button>
        </form>
      </div>

      {/* --- LISTA DE TAREAS --- */}
      <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
        {semana.map((tareasDelDia, index) => (
          <TarjetaDia 
            key={index} 
            nombre={nombresDias[index]} 
            tareas={tareasDelDia} 
            diaIndex={index}
            onEliminar={eliminarTarea}
            onModificar={modificarTarea}
          />
        ))}
      </div>
    </div>
  )
}
export default App;