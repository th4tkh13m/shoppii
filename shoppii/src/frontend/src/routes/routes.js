import config from '../config'

// Pages
import Home from '../pages/Home'
import Products from '../pages/Products'

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

const privateRoutes = []

export { publicRoutes, privateRoutes }
