import SideBar from '../SideBar'

function PrivateLayout({ children }) {
    return (
        <div>
            <SideBar />
            <div className="container">{children}</div>
        </div>
    )
}

export default PrivateLayout
