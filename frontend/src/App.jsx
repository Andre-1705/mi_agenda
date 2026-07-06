import { useEffect, useState } from 'react'

const nombresDias = ["Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo"];

function App() {
  const [semana, setSemana] = useState([]);
  
  // Estados para el formulario de alta
  const [nuevaTarea, setNuevaTarea] = useState({ dia: "", hora: "", texto: "" });

  // 1. Función para traer las tareas
  const obtenerTareas = () => {
    fetch("http://localhost:8081/tareas")
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

    fetch("http://localhost:8081/tareas", {
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
    fetch(`http://localhost:8081/tareas/${dia}/${numTarea}`, {
      method: "DELETE"
    })
    .then(() => obtenerTareas());
  };

  // 4. Función para MODIFICAR (PUT)
//  const modificarTarea = (dia, numTarea) => {
//    const nuevaHora = window.prompt("Nueva hora (ej: 10:00):");
//    if (!nuevaHora) return;
//    const nuevoTexto = window.prompt("Nuevo texto de la tarea:");
//    if (!nuevoTexto) return;

//    fetch(`http://localhost:8081/tareas/${dia}/${numTarea}`, {
//      method: "PUT",
//      headers: { "Content-Type": "application/json" },
//      body: JSON.stringify({ hora: nuevaHora, texto: nuevoTexto })
//    })
//    .then(() => obtenerTareas());
//  };
  const modificarTarea = (dia, numTarea) => {
    // PRUEBA: Modificamos la tarea por datos duros para ver si el backend funciona
    fetch(`http://localhost:8081/tareas/${dia}/${numTarea}`, {
      method: "PUT",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ hora: "99:99", texto: "PUT FUNCIONA!" })
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
          <div className="flex-[2]">
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
          <div key={index} className="bg-white p-6 rounded-lg shadow-md border border-gray-200">
            
            <h2 className="text-xl font-bold text-blue-600 mb-4 border-b pb-2">
              {nombresDias[index]}
            </h2>

            <ul className="space-y-2">
              {tareasDelDia.length === 0 ? (
                <li className="text-gray-400 text-sm">Sin tareas</li>
              ) : (
                tareasDelDia.map((tarea, indexTarea) => (
                  <li key={indexTarea} className="text-gray-700 bg-gray-50 p-2 rounded flex justify-between items-center">
                    <span>{tarea.hora} - {tarea.texto}</span>
                    
                    <div className="flex gap-2">
                      <button 
                        onClick={() => modificarTarea(index, indexTarea)} 
                        className="text-blue-500 hover:text-blue-700 text-sm font-bold"
                        title="Modificar"
                      >
                        ✏️
                      </button>
                      <button 
                        onClick={() => eliminarTarea(index, indexTarea)} 
                        className="text-red-500 hover:text-red-700 text-sm font-bold"
                        title="Eliminar"
                      >
                        X
                      </button>
                    </div>

                  </li>
                ))
              )}
            </ul>

          </div>
        ))}
      </div>
    </div>
  )
}

export default App