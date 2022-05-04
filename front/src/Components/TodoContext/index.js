import React, { useState, useEffect } from 'react';
import todoServices from '../../services/todoServices';

const TodoContext = React.createContext();

function TodoProvider(props) {
  const [error, setError] = useState(false);
  const [loading, setLoading] = useState(true);

  const [searchValue, setSearchValue] = useState("");
  const [openModal, setOpenModal] = useState(false);

  const [todos, setTodos] = useState([]);
  const [refresh, setRefresh] = useState(false);

  useEffect(() => {
    todoServices
      .getAll()
      .then((response) => {
        setTodos(response.data);
        setLoading(false);
      })
      .catch((e) => {
        setLoading(false);
        setError(e);
        console.log(e);
      });

    return () => {
      setRefresh(false);
    };
  }, [refresh]);

  let searchedTodos = [];

  if (!searchValue.length >= 1) {
    searchedTodos = todos;
  } else {
    searchedTodos = todos.filter(todo => {
      const todoText = todo.text.toLowerCase();
      const searchText = searchValue.toLowerCase();
      return todoText.includes(searchText);
    });
  }

  const addTodo = (text) => {
    let newTodo = { text: text, completed: false };
    todoServices
      .create(newTodo)
      .then((response) => {
        console.log(`Todo created, status:${response.status}`);
        setRefresh(true);
      })
      .catch((e) => {
        console.log(e);
      });
  };

  const completeTodo = (id, newCompleted) => {
    todoServices
      .updateCompleted(id, { completed: newCompleted })
      .then((response) => {
        console.log(`Completed from todo updated, status:${response.status}`);
        setRefresh(true);
      })
      .catch((e) => {
        console.log(e);
      });
  };

  const deleteTodo = (id) => {
    todoServices
      .delete(id)
      .then((response) => {
        console.log(`Todo deleted, status:${response.status}`);
        setRefresh(true);
      })
      .catch((e) => {
        console.log(e);
      });
  };

  const completedTodos = todos.filter((todo) => !!todo.completed).length;
  const totalTodos = todos.length;

  return (
    <TodoContext.Provider value={{
      loading,
      error,
      totalTodos,
      completedTodos,
      searchValue,
      setSearchValue,
      searchedTodos,
      addTodo,
      completeTodo,
      deleteTodo,
      openModal,
      setOpenModal,
    }}>
      {props.children}
    </TodoContext.Provider>
  );
}

export { TodoContext, TodoProvider };
