export interface Seller {
    id?: number;
    firstName: string;
    lastName: string;
    type: string;
    phoneNumber: string;
    createAccount: Date;
    isPremium?: boolean;
    premiumAccount: Date;
    lastAddvertisement: Date;
}