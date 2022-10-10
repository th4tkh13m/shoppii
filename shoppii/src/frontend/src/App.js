import { useState } from 'react'
import { publicRoutes, privateRoutes } from './routes/routes'
import { Routes, Route } from 'react-router-dom'
import DefaultLayout from './layouts/DefaultLayout'
import PrivateLayout from './layouts/PrivateLayout'
import { useAuth } from './hooks/useAuth'

function App() {
    const { role } = useAuth()
    const [routes, setRoutes] = useState(() => {
        const routes = role === 'admin' ? privateRoutes : publicRoutes
        return routes
    })
    console.log(role)
    console.log(routes)
    return (
        <div className="App">
            <Routes>
                {routes.map((route, index) => {
                    const Layout =
                        role === 'admin' ? PrivateLayout : DefaultLayout
                    const Page = route.component
                    return (
                        <Route
                            key={index}
                            path={route.path}
                            element={
                                <Layout>
                                    <Page />
                                </Layout>
                            }
                        />
                    )
                })}
            </Routes>
        </div>
    )
}

export default App
