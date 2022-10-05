// Pages
// User
import Home from '../pages/user/Home'
import Products from '../pages/user/Products'

// Shop

// Admin
import Dashboard from '../pages/admin/Dashboard'
import Requests from '../pages/admin/Requests'

// Routes
const publicRoutes = [
    {
        path: '/',
        component: Home,
    },
    {
        path: '/products',
        component: Products,
    },
]

const privateRoutes = [
    {
        path: '/admin',
        component: Dashboard,
    },
    {
        path: '/admin/requests',
        component: Requests,
    },
]

export { publicRoutes, privateRoutes }
