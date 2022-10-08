import React from 'react'
import ChangPassword from './components/ChangePass'
import EditProfileForm from './components/EditProfileForm'
import OrderHistoryItem from './components/OrderHistoryItem'
import ProfileBox from './components/ProfileBox'
import ProfileSideBar from './components/ProfileSidebar'
import PurchasedProduct from './components/PurchasedProduct'


function Profile() {
    const [content, setContent] = React.useState("")

    const getActionFromSidebar = (action) => setContent(action)
    
    return (
        <main className="container d-flex justify-content-between h-auto">
            <ProfileSideBar getAction = {getActionFromSidebar}/>
            <ProfileBox content={content}/>
        </main>
    )
}

export default Profile
