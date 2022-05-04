import { Switch, Route } from "react-router-dom";
import Jobs from "./views/Jobs/Jobs";
import PostJobs from "./views/PostJobs/PostJobs";
import Profile from "./views/Profile/Profile";
import CreateVacancies from "./views/CreateVacancies/CreateVacancies";
import Auth from "./views/Auth";
import "./App.css";

function App() {
  return (
    <div className='App'>
      <Switch>
        <Route path='/jobs' component={Jobs} />
        <Route path='/post-jobs' component={PostJobs} />
        <Route path='/account' component={Auth} />
        <Route path='/create-vacancies' component={CreateVacancies} />
        <Route path='/' component={Profile} />
      </Switch>
    </div>
  );
}

export default App;
